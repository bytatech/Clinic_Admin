package com.lxisoft.byta.web.rest;

import com.lxisoft.byta.ClinicLenusApp;

import com.lxisoft.byta.domain.Governtment;
import com.lxisoft.byta.repository.GoverntmentRepository;
import com.lxisoft.byta.service.GoverntmentService;
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
 * Test class for the GoverntmentResource REST controller.
 *
 * @see GoverntmentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClinicLenusApp.class)
public class GoverntmentResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private GoverntmentRepository governtmentRepository;

    @Autowired
    private GoverntmentService governtmentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGoverntmentMockMvc;

    private Governtment governtment;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        GoverntmentResource governtmentResource = new GoverntmentResource(governtmentService);
        this.restGoverntmentMockMvc = MockMvcBuilders.standaloneSetup(governtmentResource)
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
    public static Governtment createEntity(EntityManager em) {
        Governtment governtment = new Governtment()
            .name(DEFAULT_NAME);
        return governtment;
    }

    @Before
    public void initTest() {
        governtment = createEntity(em);
    }

    @Test
    @Transactional
    public void createGoverntment() throws Exception {
        int databaseSizeBeforeCreate = governtmentRepository.findAll().size();

        // Create the Governtment
        restGoverntmentMockMvc.perform(post("/api/governtments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(governtment)))
            .andExpect(status().isCreated());

        // Validate the Governtment in the database
        List<Governtment> governtmentList = governtmentRepository.findAll();
        assertThat(governtmentList).hasSize(databaseSizeBeforeCreate + 1);
        Governtment testGoverntment = governtmentList.get(governtmentList.size() - 1);
        assertThat(testGoverntment.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createGoverntmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = governtmentRepository.findAll().size();

        // Create the Governtment with an existing ID
        governtment.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGoverntmentMockMvc.perform(post("/api/governtments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(governtment)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Governtment> governtmentList = governtmentRepository.findAll();
        assertThat(governtmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGoverntments() throws Exception {
        // Initialize the database
        governtmentRepository.saveAndFlush(governtment);

        // Get all the governtmentList
        restGoverntmentMockMvc.perform(get("/api/governtments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(governtment.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getGoverntment() throws Exception {
        // Initialize the database
        governtmentRepository.saveAndFlush(governtment);

        // Get the governtment
        restGoverntmentMockMvc.perform(get("/api/governtments/{id}", governtment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(governtment.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGoverntment() throws Exception {
        // Get the governtment
        restGoverntmentMockMvc.perform(get("/api/governtments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGoverntment() throws Exception {
        // Initialize the database
        governtmentService.save(governtment);

        int databaseSizeBeforeUpdate = governtmentRepository.findAll().size();

        // Update the governtment
        Governtment updatedGoverntment = governtmentRepository.findOne(governtment.getId());
        updatedGoverntment
            .name(UPDATED_NAME);

        restGoverntmentMockMvc.perform(put("/api/governtments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGoverntment)))
            .andExpect(status().isOk());

        // Validate the Governtment in the database
        List<Governtment> governtmentList = governtmentRepository.findAll();
        assertThat(governtmentList).hasSize(databaseSizeBeforeUpdate);
        Governtment testGoverntment = governtmentList.get(governtmentList.size() - 1);
        assertThat(testGoverntment.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingGoverntment() throws Exception {
        int databaseSizeBeforeUpdate = governtmentRepository.findAll().size();

        // Create the Governtment

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGoverntmentMockMvc.perform(put("/api/governtments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(governtment)))
            .andExpect(status().isCreated());

        // Validate the Governtment in the database
        List<Governtment> governtmentList = governtmentRepository.findAll();
        assertThat(governtmentList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteGoverntment() throws Exception {
        // Initialize the database
        governtmentService.save(governtment);

        int databaseSizeBeforeDelete = governtmentRepository.findAll().size();

        // Get the governtment
        restGoverntmentMockMvc.perform(delete("/api/governtments/{id}", governtment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Governtment> governtmentList = governtmentRepository.findAll();
        assertThat(governtmentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Governtment.class);
        Governtment governtment1 = new Governtment();
        governtment1.setId(1L);
        Governtment governtment2 = new Governtment();
        governtment2.setId(governtment1.getId());
        assertThat(governtment1).isEqualTo(governtment2);
        governtment2.setId(2L);
        assertThat(governtment1).isNotEqualTo(governtment2);
        governtment1.setId(null);
        assertThat(governtment1).isNotEqualTo(governtment2);
    }
}
