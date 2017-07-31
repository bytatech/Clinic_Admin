package com.lxisoft.byta.web.rest;

import com.lxisoft.byta.ClinicLenusApp;

import com.lxisoft.byta.domain.MedicineSystem;
import com.lxisoft.byta.repository.MedicineSystemRepository;
import com.lxisoft.byta.service.MedicineSystemService;
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
 * Test class for the MedicineSystemResource REST controller.
 *
 * @see MedicineSystemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClinicLenusApp.class)
public class MedicineSystemResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private MedicineSystemRepository medicineSystemRepository;

    @Autowired
    private MedicineSystemService medicineSystemService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMedicineSystemMockMvc;

    private MedicineSystem medicineSystem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MedicineSystemResource medicineSystemResource = new MedicineSystemResource(medicineSystemService);
        this.restMedicineSystemMockMvc = MockMvcBuilders.standaloneSetup(medicineSystemResource)
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
    public static MedicineSystem createEntity(EntityManager em) {
        MedicineSystem medicineSystem = new MedicineSystem()
            .name(DEFAULT_NAME);
        return medicineSystem;
    }

    @Before
    public void initTest() {
        medicineSystem = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedicineSystem() throws Exception {
        int databaseSizeBeforeCreate = medicineSystemRepository.findAll().size();

        // Create the MedicineSystem
        restMedicineSystemMockMvc.perform(post("/api/medicine-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicineSystem)))
            .andExpect(status().isCreated());

        // Validate the MedicineSystem in the database
        List<MedicineSystem> medicineSystemList = medicineSystemRepository.findAll();
        assertThat(medicineSystemList).hasSize(databaseSizeBeforeCreate + 1);
        MedicineSystem testMedicineSystem = medicineSystemList.get(medicineSystemList.size() - 1);
        assertThat(testMedicineSystem.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createMedicineSystemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicineSystemRepository.findAll().size();

        // Create the MedicineSystem with an existing ID
        medicineSystem.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicineSystemMockMvc.perform(post("/api/medicine-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicineSystem)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<MedicineSystem> medicineSystemList = medicineSystemRepository.findAll();
        assertThat(medicineSystemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMedicineSystems() throws Exception {
        // Initialize the database
        medicineSystemRepository.saveAndFlush(medicineSystem);

        // Get all the medicineSystemList
        restMedicineSystemMockMvc.perform(get("/api/medicine-systems?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medicineSystem.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getMedicineSystem() throws Exception {
        // Initialize the database
        medicineSystemRepository.saveAndFlush(medicineSystem);

        // Get the medicineSystem
        restMedicineSystemMockMvc.perform(get("/api/medicine-systems/{id}", medicineSystem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(medicineSystem.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMedicineSystem() throws Exception {
        // Get the medicineSystem
        restMedicineSystemMockMvc.perform(get("/api/medicine-systems/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicineSystem() throws Exception {
        // Initialize the database
        medicineSystemService.save(medicineSystem);

        int databaseSizeBeforeUpdate = medicineSystemRepository.findAll().size();

        // Update the medicineSystem
        MedicineSystem updatedMedicineSystem = medicineSystemRepository.findOne(medicineSystem.getId());
        updatedMedicineSystem
            .name(UPDATED_NAME);

        restMedicineSystemMockMvc.perform(put("/api/medicine-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMedicineSystem)))
            .andExpect(status().isOk());

        // Validate the MedicineSystem in the database
        List<MedicineSystem> medicineSystemList = medicineSystemRepository.findAll();
        assertThat(medicineSystemList).hasSize(databaseSizeBeforeUpdate);
        MedicineSystem testMedicineSystem = medicineSystemList.get(medicineSystemList.size() - 1);
        assertThat(testMedicineSystem.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMedicineSystem() throws Exception {
        int databaseSizeBeforeUpdate = medicineSystemRepository.findAll().size();

        // Create the MedicineSystem

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMedicineSystemMockMvc.perform(put("/api/medicine-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medicineSystem)))
            .andExpect(status().isCreated());

        // Validate the MedicineSystem in the database
        List<MedicineSystem> medicineSystemList = medicineSystemRepository.findAll();
        assertThat(medicineSystemList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMedicineSystem() throws Exception {
        // Initialize the database
        medicineSystemService.save(medicineSystem);

        int databaseSizeBeforeDelete = medicineSystemRepository.findAll().size();

        // Get the medicineSystem
        restMedicineSystemMockMvc.perform(delete("/api/medicine-systems/{id}", medicineSystem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MedicineSystem> medicineSystemList = medicineSystemRepository.findAll();
        assertThat(medicineSystemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicineSystem.class);
        MedicineSystem medicineSystem1 = new MedicineSystem();
        medicineSystem1.setId(1L);
        MedicineSystem medicineSystem2 = new MedicineSystem();
        medicineSystem2.setId(medicineSystem1.getId());
        assertThat(medicineSystem1).isEqualTo(medicineSystem2);
        medicineSystem2.setId(2L);
        assertThat(medicineSystem1).isNotEqualTo(medicineSystem2);
        medicineSystem1.setId(null);
        assertThat(medicineSystem1).isNotEqualTo(medicineSystem2);
    }
}
