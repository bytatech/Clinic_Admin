package com.lxisoft.byta.web.rest;

import com.lxisoft.byta.ClinicLenusApp;

import com.lxisoft.byta.domain.Drugs;
import com.lxisoft.byta.repository.DrugsRepository;
import com.lxisoft.byta.service.DrugsService;
import com.lxisoft.byta.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DrugsResource REST controller.
 *
 * @see DrugsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClinicLenusApp.class)
public class DrugsResourceIntTest {

    private static final String DEFAULT_DRUG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DRUG_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DRUG_INSTRUCTION = "AAAAAAAAAA";
    private static final String UPDATED_DRUG_INSTRUCTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_FREQUENCY = 1;
    private static final Integer UPDATED_FREQUENCY = 2;

    private static final Integer DEFAULT_TOTAL_PACKING = 1;
    private static final Integer UPDATED_TOTAL_PACKING = 2;

    @Autowired
    private DrugsRepository drugsRepository;

    @Autowired
    private DrugsService drugsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDrugsMockMvc;

    private Drugs drugs;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DrugsResource drugsResource = new DrugsResource(drugsService);
        this.restDrugsMockMvc = MockMvcBuilders.standaloneSetup(drugsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Drugs createEntity(EntityManager em) {
        Drugs drugs = new Drugs()
            .drugName(DEFAULT_DRUG_NAME)
            .drugInstruction(DEFAULT_DRUG_INSTRUCTION)
            .frequency(DEFAULT_FREQUENCY)
            .totalPacking(DEFAULT_TOTAL_PACKING);
        return drugs;
    }

    @Before
    public void initTest() {
        drugs = createEntity(em);
    }

    @Test
    @Transactional
    public void createDrugs() throws Exception {
        int databaseSizeBeforeCreate = drugsRepository.findAll().size();

        // Create the Drugs
        restDrugsMockMvc.perform(post("/api/drugs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(drugs)))
            .andExpect(status().isCreated());

        // Validate the Drugs in the database
        List<Drugs> drugsList = drugsRepository.findAll();
        assertThat(drugsList).hasSize(databaseSizeBeforeCreate + 1);
        Drugs testDrugs = drugsList.get(drugsList.size() - 1);
        assertThat(testDrugs.getDrugName()).isEqualTo(DEFAULT_DRUG_NAME);
        assertThat(testDrugs.getDrugInstruction()).isEqualTo(DEFAULT_DRUG_INSTRUCTION);
        assertThat(testDrugs.getFrequency()).isEqualTo(DEFAULT_FREQUENCY);
        assertThat(testDrugs.getTotalPacking()).isEqualTo(DEFAULT_TOTAL_PACKING);
    }

    @Test
    @Transactional
    public void createDrugsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = drugsRepository.findAll().size();

        // Create the Drugs with an existing ID
        drugs.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDrugsMockMvc.perform(post("/api/drugs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(drugs)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Drugs> drugsList = drugsRepository.findAll();
        assertThat(drugsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDrugNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = drugsRepository.findAll().size();
        // set the field null
        drugs.setDrugName(null);

        // Create the Drugs, which fails.

        restDrugsMockMvc.perform(post("/api/drugs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(drugs)))
            .andExpect(status().isBadRequest());

        List<Drugs> drugsList = drugsRepository.findAll();
        assertThat(drugsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDrugs() throws Exception {
        // Initialize the database
        drugsRepository.saveAndFlush(drugs);

        // Get all the drugsList
        restDrugsMockMvc.perform(get("/api/drugs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(drugs.getId().intValue())))
            .andExpect(jsonPath("$.[*].drugName").value(hasItem(DEFAULT_DRUG_NAME.toString())))
            .andExpect(jsonPath("$.[*].drugInstruction").value(hasItem(DEFAULT_DRUG_INSTRUCTION.toString())))
            .andExpect(jsonPath("$.[*].frequency").value(hasItem(DEFAULT_FREQUENCY)))
            .andExpect(jsonPath("$.[*].totalPacking").value(hasItem(DEFAULT_TOTAL_PACKING)));
    }

    @Test
    @Transactional
    public void getDrugs() throws Exception {
        // Initialize the database
        drugsRepository.saveAndFlush(drugs);

        // Get the drugs
        restDrugsMockMvc.perform(get("/api/drugs/{id}", drugs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(drugs.getId().intValue()))
            .andExpect(jsonPath("$.drugName").value(DEFAULT_DRUG_NAME.toString()))
            .andExpect(jsonPath("$.drugInstruction").value(DEFAULT_DRUG_INSTRUCTION.toString()))
            .andExpect(jsonPath("$.frequency").value(DEFAULT_FREQUENCY))
            .andExpect(jsonPath("$.totalPacking").value(DEFAULT_TOTAL_PACKING));
    }

    @Test
    @Transactional
    public void getNonExistingDrugs() throws Exception {
        // Get the drugs
        restDrugsMockMvc.perform(get("/api/drugs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDrugs() throws Exception {
        // Initialize the database
        drugsService.save(drugs);

        int databaseSizeBeforeUpdate = drugsRepository.findAll().size();

        // Update the drugs
        Drugs updatedDrugs = drugsRepository.findOne(drugs.getId());
        updatedDrugs
            .drugName(UPDATED_DRUG_NAME)
            .drugInstruction(UPDATED_DRUG_INSTRUCTION)
            .frequency(UPDATED_FREQUENCY)
            .totalPacking(UPDATED_TOTAL_PACKING);

        restDrugsMockMvc.perform(put("/api/drugs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDrugs)))
            .andExpect(status().isOk());

        // Validate the Drugs in the database
        List<Drugs> drugsList = drugsRepository.findAll();
        assertThat(drugsList).hasSize(databaseSizeBeforeUpdate);
        Drugs testDrugs = drugsList.get(drugsList.size() - 1);
        assertThat(testDrugs.getDrugName()).isEqualTo(UPDATED_DRUG_NAME);
        assertThat(testDrugs.getDrugInstruction()).isEqualTo(UPDATED_DRUG_INSTRUCTION);
        assertThat(testDrugs.getFrequency()).isEqualTo(UPDATED_FREQUENCY);
        assertThat(testDrugs.getTotalPacking()).isEqualTo(UPDATED_TOTAL_PACKING);
    }

    @Test
    @Transactional
    public void updateNonExistingDrugs() throws Exception {
        int databaseSizeBeforeUpdate = drugsRepository.findAll().size();

        // Create the Drugs

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDrugsMockMvc.perform(put("/api/drugs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(drugs)))
            .andExpect(status().isCreated());

        // Validate the Drugs in the database
        List<Drugs> drugsList = drugsRepository.findAll();
        assertThat(drugsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDrugs() throws Exception {
        // Initialize the database
        drugsService.save(drugs);

        int databaseSizeBeforeDelete = drugsRepository.findAll().size();

        // Get the drugs
        restDrugsMockMvc.perform(delete("/api/drugs/{id}", drugs.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Drugs> drugsList = drugsRepository.findAll();
        assertThat(drugsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Drugs.class);
        Drugs drugs1 = new Drugs();
        drugs1.setId(1L);
        Drugs drugs2 = new Drugs();
        drugs2.setId(drugs1.getId());
        assertThat(drugs1).isEqualTo(drugs2);
        drugs2.setId(2L);
        assertThat(drugs1).isNotEqualTo(drugs2);
        drugs1.setId(null);
        assertThat(drugs1).isNotEqualTo(drugs2);
    }
}
