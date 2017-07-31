package com.lxisoft.byta.web.rest;

import com.lxisoft.byta.ClinicLenusApp;

import com.lxisoft.byta.domain.ClinicPatientData;
import com.lxisoft.byta.repository.ClinicPatientDataRepository;
import com.lxisoft.byta.service.ClinicPatientDataService;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ClinicPatientDataResource REST controller.
 *
 * @see ClinicPatientDataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClinicLenusApp.class)
public class ClinicPatientDataResourceIntTest {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;

    private static final Long DEFAULT_PHONE_NO = 1L;
    private static final Long UPDATED_PHONE_NO = 2L;

    @Autowired
    private ClinicPatientDataRepository clinicPatientDataRepository;

    @Autowired
    private ClinicPatientDataService clinicPatientDataService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restClinicPatientDataMockMvc;

    private ClinicPatientData clinicPatientData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ClinicPatientDataResource clinicPatientDataResource = new ClinicPatientDataResource(clinicPatientDataService);
        this.restClinicPatientDataMockMvc = MockMvcBuilders.standaloneSetup(clinicPatientDataResource)
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
    public static ClinicPatientData createEntity(EntityManager em) {
        ClinicPatientData clinicPatientData = new ClinicPatientData()
            .date(DEFAULT_DATE)
            .name(DEFAULT_NAME)
            .gender(DEFAULT_GENDER)
            .age(DEFAULT_AGE)
            .phoneNo(DEFAULT_PHONE_NO);
        return clinicPatientData;
    }

    @Before
    public void initTest() {
        clinicPatientData = createEntity(em);
    }

    @Test
    @Transactional
    public void createClinicPatientData() throws Exception {
        int databaseSizeBeforeCreate = clinicPatientDataRepository.findAll().size();

        // Create the ClinicPatientData
        restClinicPatientDataMockMvc.perform(post("/api/clinic-patient-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clinicPatientData)))
            .andExpect(status().isCreated());

        // Validate the ClinicPatientData in the database
        List<ClinicPatientData> clinicPatientDataList = clinicPatientDataRepository.findAll();
        assertThat(clinicPatientDataList).hasSize(databaseSizeBeforeCreate + 1);
        ClinicPatientData testClinicPatientData = clinicPatientDataList.get(clinicPatientDataList.size() - 1);
        assertThat(testClinicPatientData.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testClinicPatientData.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testClinicPatientData.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testClinicPatientData.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testClinicPatientData.getPhoneNo()).isEqualTo(DEFAULT_PHONE_NO);
    }

    @Test
    @Transactional
    public void createClinicPatientDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = clinicPatientDataRepository.findAll().size();

        // Create the ClinicPatientData with an existing ID
        clinicPatientData.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClinicPatientDataMockMvc.perform(post("/api/clinic-patient-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clinicPatientData)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ClinicPatientData> clinicPatientDataList = clinicPatientDataRepository.findAll();
        assertThat(clinicPatientDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = clinicPatientDataRepository.findAll().size();
        // set the field null
        clinicPatientData.setName(null);

        // Create the ClinicPatientData, which fails.

        restClinicPatientDataMockMvc.perform(post("/api/clinic-patient-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clinicPatientData)))
            .andExpect(status().isBadRequest());

        List<ClinicPatientData> clinicPatientDataList = clinicPatientDataRepository.findAll();
        assertThat(clinicPatientDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllClinicPatientData() throws Exception {
        // Initialize the database
        clinicPatientDataRepository.saveAndFlush(clinicPatientData);

        // Get all the clinicPatientDataList
        restClinicPatientDataMockMvc.perform(get("/api/clinic-patient-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clinicPatientData.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].phoneNo").value(hasItem(DEFAULT_PHONE_NO.intValue())));
    }

    @Test
    @Transactional
    public void getClinicPatientData() throws Exception {
        // Initialize the database
        clinicPatientDataRepository.saveAndFlush(clinicPatientData);

        // Get the clinicPatientData
        restClinicPatientDataMockMvc.perform(get("/api/clinic-patient-data/{id}", clinicPatientData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(clinicPatientData.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.phoneNo").value(DEFAULT_PHONE_NO.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingClinicPatientData() throws Exception {
        // Get the clinicPatientData
        restClinicPatientDataMockMvc.perform(get("/api/clinic-patient-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClinicPatientData() throws Exception {
        // Initialize the database
        clinicPatientDataService.save(clinicPatientData);

        int databaseSizeBeforeUpdate = clinicPatientDataRepository.findAll().size();

        // Update the clinicPatientData
        ClinicPatientData updatedClinicPatientData = clinicPatientDataRepository.findOne(clinicPatientData.getId());
        updatedClinicPatientData
            .date(UPDATED_DATE)
            .name(UPDATED_NAME)
            .gender(UPDATED_GENDER)
            .age(UPDATED_AGE)
            .phoneNo(UPDATED_PHONE_NO);

        restClinicPatientDataMockMvc.perform(put("/api/clinic-patient-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedClinicPatientData)))
            .andExpect(status().isOk());

        // Validate the ClinicPatientData in the database
        List<ClinicPatientData> clinicPatientDataList = clinicPatientDataRepository.findAll();
        assertThat(clinicPatientDataList).hasSize(databaseSizeBeforeUpdate);
        ClinicPatientData testClinicPatientData = clinicPatientDataList.get(clinicPatientDataList.size() - 1);
        assertThat(testClinicPatientData.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testClinicPatientData.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testClinicPatientData.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testClinicPatientData.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testClinicPatientData.getPhoneNo()).isEqualTo(UPDATED_PHONE_NO);
    }

    @Test
    @Transactional
    public void updateNonExistingClinicPatientData() throws Exception {
        int databaseSizeBeforeUpdate = clinicPatientDataRepository.findAll().size();

        // Create the ClinicPatientData

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restClinicPatientDataMockMvc.perform(put("/api/clinic-patient-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clinicPatientData)))
            .andExpect(status().isCreated());

        // Validate the ClinicPatientData in the database
        List<ClinicPatientData> clinicPatientDataList = clinicPatientDataRepository.findAll();
        assertThat(clinicPatientDataList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteClinicPatientData() throws Exception {
        // Initialize the database
        clinicPatientDataService.save(clinicPatientData);

        int databaseSizeBeforeDelete = clinicPatientDataRepository.findAll().size();

        // Get the clinicPatientData
        restClinicPatientDataMockMvc.perform(delete("/api/clinic-patient-data/{id}", clinicPatientData.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ClinicPatientData> clinicPatientDataList = clinicPatientDataRepository.findAll();
        assertThat(clinicPatientDataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClinicPatientData.class);
        ClinicPatientData clinicPatientData1 = new ClinicPatientData();
        clinicPatientData1.setId(1L);
        ClinicPatientData clinicPatientData2 = new ClinicPatientData();
        clinicPatientData2.setId(clinicPatientData1.getId());
        assertThat(clinicPatientData1).isEqualTo(clinicPatientData2);
        clinicPatientData2.setId(2L);
        assertThat(clinicPatientData1).isNotEqualTo(clinicPatientData2);
        clinicPatientData1.setId(null);
        assertThat(clinicPatientData1).isNotEqualTo(clinicPatientData2);
    }
}
