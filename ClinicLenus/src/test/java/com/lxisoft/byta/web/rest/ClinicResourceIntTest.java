package com.lxisoft.byta.web.rest;

import com.lxisoft.byta.ClinicLenusApp;

import com.lxisoft.byta.domain.Clinic;
import com.lxisoft.byta.repository.ClinicRepository;
import com.lxisoft.byta.service.ClinicService;
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
 * Test class for the ClinicResource REST controller.
 *
 * @see ClinicResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClinicLenusApp.class)
public class ClinicResourceIntTest {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restClinicMockMvc;

    private Clinic clinic;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ClinicResource clinicResource = new ClinicResource(clinicService);
        this.restClinicMockMvc = MockMvcBuilders.standaloneSetup(clinicResource)
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
    public static Clinic createEntity(EntityManager em) {
        Clinic clinic = new Clinic();
        return clinic;
    }

    @Before
    public void initTest() {
        clinic = createEntity(em);
    }

    @Test
    @Transactional
    public void createClinic() throws Exception {
        int databaseSizeBeforeCreate = clinicRepository.findAll().size();

        // Create the Clinic
        restClinicMockMvc.perform(post("/api/clinics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clinic)))
            .andExpect(status().isCreated());

        // Validate the Clinic in the database
        List<Clinic> clinicList = clinicRepository.findAll();
        assertThat(clinicList).hasSize(databaseSizeBeforeCreate + 1);
        Clinic testClinic = clinicList.get(clinicList.size() - 1);
    }

    @Test
    @Transactional
    public void createClinicWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = clinicRepository.findAll().size();

        // Create the Clinic with an existing ID
        clinic.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClinicMockMvc.perform(post("/api/clinics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clinic)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Clinic> clinicList = clinicRepository.findAll();
        assertThat(clinicList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllClinics() throws Exception {
        // Initialize the database
        clinicRepository.saveAndFlush(clinic);

        // Get all the clinicList
        restClinicMockMvc.perform(get("/api/clinics?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clinic.getId().intValue())));
    }

    @Test
    @Transactional
    public void getClinic() throws Exception {
        // Initialize the database
        clinicRepository.saveAndFlush(clinic);

        // Get the clinic
        restClinicMockMvc.perform(get("/api/clinics/{id}", clinic.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(clinic.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingClinic() throws Exception {
        // Get the clinic
        restClinicMockMvc.perform(get("/api/clinics/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClinic() throws Exception {
        // Initialize the database
        clinicService.save(clinic);

        int databaseSizeBeforeUpdate = clinicRepository.findAll().size();

        // Update the clinic
        Clinic updatedClinic = clinicRepository.findOne(clinic.getId());

        restClinicMockMvc.perform(put("/api/clinics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedClinic)))
            .andExpect(status().isOk());

        // Validate the Clinic in the database
        List<Clinic> clinicList = clinicRepository.findAll();
        assertThat(clinicList).hasSize(databaseSizeBeforeUpdate);
        Clinic testClinic = clinicList.get(clinicList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingClinic() throws Exception {
        int databaseSizeBeforeUpdate = clinicRepository.findAll().size();

        // Create the Clinic

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restClinicMockMvc.perform(put("/api/clinics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clinic)))
            .andExpect(status().isCreated());

        // Validate the Clinic in the database
        List<Clinic> clinicList = clinicRepository.findAll();
        assertThat(clinicList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteClinic() throws Exception {
        // Initialize the database
        clinicService.save(clinic);

        int databaseSizeBeforeDelete = clinicRepository.findAll().size();

        // Get the clinic
        restClinicMockMvc.perform(delete("/api/clinics/{id}", clinic.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Clinic> clinicList = clinicRepository.findAll();
        assertThat(clinicList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Clinic.class);
        Clinic clinic1 = new Clinic();
        clinic1.setId(1L);
        Clinic clinic2 = new Clinic();
        clinic2.setId(clinic1.getId());
        assertThat(clinic1).isEqualTo(clinic2);
        clinic2.setId(2L);
        assertThat(clinic1).isNotEqualTo(clinic2);
        clinic1.setId(null);
        assertThat(clinic1).isNotEqualTo(clinic2);
    }
}
