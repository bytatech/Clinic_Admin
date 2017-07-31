package com.lxisoft.byta.web.rest;

import com.lxisoft.byta.ClinicLenusApp;

import com.lxisoft.byta.domain.ClinicPatientPrivateData;
import com.lxisoft.byta.repository.ClinicPatientPrivateDataRepository;
import com.lxisoft.byta.service.ClinicPatientPrivateDataService;
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
 * Test class for the ClinicPatientPrivateDataResource REST controller.
 *
 * @see ClinicPatientPrivateDataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClinicLenusApp.class)
public class ClinicPatientPrivateDataResourceIntTest {

    private static final Integer DEFAULT_PATIENT_ID = 1;
    private static final Integer UPDATED_PATIENT_ID = 2;

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final Long DEFAULT_ZIP = 1L;
    private static final Long UPDATED_ZIP = 2L;

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_SOCIAL_MEDIA_ID = "AAAAAAAAAA";
    private static final String UPDATED_SOCIAL_MEDIA_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PROFESSION = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSION = "BBBBBBBBBB";

    @Autowired
    private ClinicPatientPrivateDataRepository clinicPatientPrivateDataRepository;

    @Autowired
    private ClinicPatientPrivateDataService clinicPatientPrivateDataService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restClinicPatientPrivateDataMockMvc;

    private ClinicPatientPrivateData clinicPatientPrivateData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ClinicPatientPrivateDataResource clinicPatientPrivateDataResource = new ClinicPatientPrivateDataResource(clinicPatientPrivateDataService);
        this.restClinicPatientPrivateDataMockMvc = MockMvcBuilders.standaloneSetup(clinicPatientPrivateDataResource)
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
    public static ClinicPatientPrivateData createEntity(EntityManager em) {
        ClinicPatientPrivateData clinicPatientPrivateData = new ClinicPatientPrivateData()
            .patientId(DEFAULT_PATIENT_ID)
            .address(DEFAULT_ADDRESS)
            .city(DEFAULT_CITY)
            .zip(DEFAULT_ZIP)
            .email(DEFAULT_EMAIL)
            .country(DEFAULT_COUNTRY)
            .socialMediaId(DEFAULT_SOCIAL_MEDIA_ID)
            .profession(DEFAULT_PROFESSION);
        return clinicPatientPrivateData;
    }

    @Before
    public void initTest() {
        clinicPatientPrivateData = createEntity(em);
    }

    @Test
    @Transactional
    public void createClinicPatientPrivateData() throws Exception {
        int databaseSizeBeforeCreate = clinicPatientPrivateDataRepository.findAll().size();

        // Create the ClinicPatientPrivateData
        restClinicPatientPrivateDataMockMvc.perform(post("/api/clinic-patient-private-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clinicPatientPrivateData)))
            .andExpect(status().isCreated());

        // Validate the ClinicPatientPrivateData in the database
        List<ClinicPatientPrivateData> clinicPatientPrivateDataList = clinicPatientPrivateDataRepository.findAll();
        assertThat(clinicPatientPrivateDataList).hasSize(databaseSizeBeforeCreate + 1);
        ClinicPatientPrivateData testClinicPatientPrivateData = clinicPatientPrivateDataList.get(clinicPatientPrivateDataList.size() - 1);
        assertThat(testClinicPatientPrivateData.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testClinicPatientPrivateData.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testClinicPatientPrivateData.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testClinicPatientPrivateData.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testClinicPatientPrivateData.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testClinicPatientPrivateData.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testClinicPatientPrivateData.getSocialMediaId()).isEqualTo(DEFAULT_SOCIAL_MEDIA_ID);
        assertThat(testClinicPatientPrivateData.getProfession()).isEqualTo(DEFAULT_PROFESSION);
    }

    @Test
    @Transactional
    public void createClinicPatientPrivateDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = clinicPatientPrivateDataRepository.findAll().size();

        // Create the ClinicPatientPrivateData with an existing ID
        clinicPatientPrivateData.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClinicPatientPrivateDataMockMvc.perform(post("/api/clinic-patient-private-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clinicPatientPrivateData)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ClinicPatientPrivateData> clinicPatientPrivateDataList = clinicPatientPrivateDataRepository.findAll();
        assertThat(clinicPatientPrivateDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllClinicPatientPrivateData() throws Exception {
        // Initialize the database
        clinicPatientPrivateDataRepository.saveAndFlush(clinicPatientPrivateData);

        // Get all the clinicPatientPrivateDataList
        restClinicPatientPrivateDataMockMvc.perform(get("/api/clinic-patient-private-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clinicPatientPrivateData.getId().intValue())))
            .andExpect(jsonPath("$.[*].patientId").value(hasItem(DEFAULT_PATIENT_ID)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].zip").value(hasItem(DEFAULT_ZIP.intValue())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].socialMediaId").value(hasItem(DEFAULT_SOCIAL_MEDIA_ID.toString())))
            .andExpect(jsonPath("$.[*].profession").value(hasItem(DEFAULT_PROFESSION.toString())));
    }

    @Test
    @Transactional
    public void getClinicPatientPrivateData() throws Exception {
        // Initialize the database
        clinicPatientPrivateDataRepository.saveAndFlush(clinicPatientPrivateData);

        // Get the clinicPatientPrivateData
        restClinicPatientPrivateDataMockMvc.perform(get("/api/clinic-patient-private-data/{id}", clinicPatientPrivateData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(clinicPatientPrivateData.getId().intValue()))
            .andExpect(jsonPath("$.patientId").value(DEFAULT_PATIENT_ID))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.zip").value(DEFAULT_ZIP.intValue()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.socialMediaId").value(DEFAULT_SOCIAL_MEDIA_ID.toString()))
            .andExpect(jsonPath("$.profession").value(DEFAULT_PROFESSION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingClinicPatientPrivateData() throws Exception {
        // Get the clinicPatientPrivateData
        restClinicPatientPrivateDataMockMvc.perform(get("/api/clinic-patient-private-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClinicPatientPrivateData() throws Exception {
        // Initialize the database
        clinicPatientPrivateDataService.save(clinicPatientPrivateData);

        int databaseSizeBeforeUpdate = clinicPatientPrivateDataRepository.findAll().size();

        // Update the clinicPatientPrivateData
        ClinicPatientPrivateData updatedClinicPatientPrivateData = clinicPatientPrivateDataRepository.findOne(clinicPatientPrivateData.getId());
        updatedClinicPatientPrivateData
            .patientId(UPDATED_PATIENT_ID)
            .address(UPDATED_ADDRESS)
            .city(UPDATED_CITY)
            .zip(UPDATED_ZIP)
            .email(UPDATED_EMAIL)
            .country(UPDATED_COUNTRY)
            .socialMediaId(UPDATED_SOCIAL_MEDIA_ID)
            .profession(UPDATED_PROFESSION);

        restClinicPatientPrivateDataMockMvc.perform(put("/api/clinic-patient-private-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedClinicPatientPrivateData)))
            .andExpect(status().isOk());

        // Validate the ClinicPatientPrivateData in the database
        List<ClinicPatientPrivateData> clinicPatientPrivateDataList = clinicPatientPrivateDataRepository.findAll();
        assertThat(clinicPatientPrivateDataList).hasSize(databaseSizeBeforeUpdate);
        ClinicPatientPrivateData testClinicPatientPrivateData = clinicPatientPrivateDataList.get(clinicPatientPrivateDataList.size() - 1);
        assertThat(testClinicPatientPrivateData.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testClinicPatientPrivateData.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testClinicPatientPrivateData.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testClinicPatientPrivateData.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testClinicPatientPrivateData.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testClinicPatientPrivateData.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testClinicPatientPrivateData.getSocialMediaId()).isEqualTo(UPDATED_SOCIAL_MEDIA_ID);
        assertThat(testClinicPatientPrivateData.getProfession()).isEqualTo(UPDATED_PROFESSION);
    }

    @Test
    @Transactional
    public void updateNonExistingClinicPatientPrivateData() throws Exception {
        int databaseSizeBeforeUpdate = clinicPatientPrivateDataRepository.findAll().size();

        // Create the ClinicPatientPrivateData

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restClinicPatientPrivateDataMockMvc.perform(put("/api/clinic-patient-private-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clinicPatientPrivateData)))
            .andExpect(status().isCreated());

        // Validate the ClinicPatientPrivateData in the database
        List<ClinicPatientPrivateData> clinicPatientPrivateDataList = clinicPatientPrivateDataRepository.findAll();
        assertThat(clinicPatientPrivateDataList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteClinicPatientPrivateData() throws Exception {
        // Initialize the database
        clinicPatientPrivateDataService.save(clinicPatientPrivateData);

        int databaseSizeBeforeDelete = clinicPatientPrivateDataRepository.findAll().size();

        // Get the clinicPatientPrivateData
        restClinicPatientPrivateDataMockMvc.perform(delete("/api/clinic-patient-private-data/{id}", clinicPatientPrivateData.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ClinicPatientPrivateData> clinicPatientPrivateDataList = clinicPatientPrivateDataRepository.findAll();
        assertThat(clinicPatientPrivateDataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClinicPatientPrivateData.class);
        ClinicPatientPrivateData clinicPatientPrivateData1 = new ClinicPatientPrivateData();
        clinicPatientPrivateData1.setId(1L);
        ClinicPatientPrivateData clinicPatientPrivateData2 = new ClinicPatientPrivateData();
        clinicPatientPrivateData2.setId(clinicPatientPrivateData1.getId());
        assertThat(clinicPatientPrivateData1).isEqualTo(clinicPatientPrivateData2);
        clinicPatientPrivateData2.setId(2L);
        assertThat(clinicPatientPrivateData1).isNotEqualTo(clinicPatientPrivateData2);
        clinicPatientPrivateData1.setId(null);
        assertThat(clinicPatientPrivateData1).isNotEqualTo(clinicPatientPrivateData2);
    }
}
