package com.lxisoft.byta.web.rest;

import com.lxisoft.byta.ClinicLenusApp;

import com.lxisoft.byta.domain.ClinicOwnerInfo;
import com.lxisoft.byta.repository.ClinicOwnerInfoRepository;
import com.lxisoft.byta.service.ClinicOwnerInfoService;
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
 * Test class for the ClinicOwnerInfoResource REST controller.
 *
 * @see ClinicOwnerInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClinicLenusApp.class)
public class ClinicOwnerInfoResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    private static final Long DEFAULT_MOBILE = 1L;
    private static final Long UPDATED_MOBILE = 2L;

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Long DEFAULT_REGISTRATION_NO = 1L;
    private static final Long UPDATED_REGISTRATION_NO = 2L;

    @Autowired
    private ClinicOwnerInfoRepository clinicOwnerInfoRepository;

    @Autowired
    private ClinicOwnerInfoService clinicOwnerInfoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restClinicOwnerInfoMockMvc;

    private ClinicOwnerInfo clinicOwnerInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ClinicOwnerInfoResource clinicOwnerInfoResource = new ClinicOwnerInfoResource(clinicOwnerInfoService);
        this.restClinicOwnerInfoMockMvc = MockMvcBuilders.standaloneSetup(clinicOwnerInfoResource)
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
    public static ClinicOwnerInfo createEntity(EntityManager em) {
        ClinicOwnerInfo clinicOwnerInfo = new ClinicOwnerInfo()
            .name(DEFAULT_NAME)
            .designation(DEFAULT_DESIGNATION)
            .mobile(DEFAULT_MOBILE)
            .email(DEFAULT_EMAIL)
            .address(DEFAULT_ADDRESS)
            .registrationNo(DEFAULT_REGISTRATION_NO);
        return clinicOwnerInfo;
    }

    @Before
    public void initTest() {
        clinicOwnerInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createClinicOwnerInfo() throws Exception {
        int databaseSizeBeforeCreate = clinicOwnerInfoRepository.findAll().size();

        // Create the ClinicOwnerInfo
        restClinicOwnerInfoMockMvc.perform(post("/api/clinic-owner-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clinicOwnerInfo)))
            .andExpect(status().isCreated());

        // Validate the ClinicOwnerInfo in the database
        List<ClinicOwnerInfo> clinicOwnerInfoList = clinicOwnerInfoRepository.findAll();
        assertThat(clinicOwnerInfoList).hasSize(databaseSizeBeforeCreate + 1);
        ClinicOwnerInfo testClinicOwnerInfo = clinicOwnerInfoList.get(clinicOwnerInfoList.size() - 1);
        assertThat(testClinicOwnerInfo.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testClinicOwnerInfo.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testClinicOwnerInfo.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testClinicOwnerInfo.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testClinicOwnerInfo.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testClinicOwnerInfo.getRegistrationNo()).isEqualTo(DEFAULT_REGISTRATION_NO);
    }

    @Test
    @Transactional
    public void createClinicOwnerInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = clinicOwnerInfoRepository.findAll().size();

        // Create the ClinicOwnerInfo with an existing ID
        clinicOwnerInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClinicOwnerInfoMockMvc.perform(post("/api/clinic-owner-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clinicOwnerInfo)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ClinicOwnerInfo> clinicOwnerInfoList = clinicOwnerInfoRepository.findAll();
        assertThat(clinicOwnerInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllClinicOwnerInfos() throws Exception {
        // Initialize the database
        clinicOwnerInfoRepository.saveAndFlush(clinicOwnerInfo);

        // Get all the clinicOwnerInfoList
        restClinicOwnerInfoMockMvc.perform(get("/api/clinic-owner-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clinicOwnerInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.intValue())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].registrationNo").value(hasItem(DEFAULT_REGISTRATION_NO.intValue())));
    }

    @Test
    @Transactional
    public void getClinicOwnerInfo() throws Exception {
        // Initialize the database
        clinicOwnerInfoRepository.saveAndFlush(clinicOwnerInfo);

        // Get the clinicOwnerInfo
        restClinicOwnerInfoMockMvc.perform(get("/api/clinic-owner-infos/{id}", clinicOwnerInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(clinicOwnerInfo.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.intValue()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.registrationNo").value(DEFAULT_REGISTRATION_NO.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingClinicOwnerInfo() throws Exception {
        // Get the clinicOwnerInfo
        restClinicOwnerInfoMockMvc.perform(get("/api/clinic-owner-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClinicOwnerInfo() throws Exception {
        // Initialize the database
        clinicOwnerInfoService.save(clinicOwnerInfo);

        int databaseSizeBeforeUpdate = clinicOwnerInfoRepository.findAll().size();

        // Update the clinicOwnerInfo
        ClinicOwnerInfo updatedClinicOwnerInfo = clinicOwnerInfoRepository.findOne(clinicOwnerInfo.getId());
        updatedClinicOwnerInfo
            .name(UPDATED_NAME)
            .designation(UPDATED_DESIGNATION)
            .mobile(UPDATED_MOBILE)
            .email(UPDATED_EMAIL)
            .address(UPDATED_ADDRESS)
            .registrationNo(UPDATED_REGISTRATION_NO);

        restClinicOwnerInfoMockMvc.perform(put("/api/clinic-owner-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedClinicOwnerInfo)))
            .andExpect(status().isOk());

        // Validate the ClinicOwnerInfo in the database
        List<ClinicOwnerInfo> clinicOwnerInfoList = clinicOwnerInfoRepository.findAll();
        assertThat(clinicOwnerInfoList).hasSize(databaseSizeBeforeUpdate);
        ClinicOwnerInfo testClinicOwnerInfo = clinicOwnerInfoList.get(clinicOwnerInfoList.size() - 1);
        assertThat(testClinicOwnerInfo.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testClinicOwnerInfo.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testClinicOwnerInfo.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testClinicOwnerInfo.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testClinicOwnerInfo.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testClinicOwnerInfo.getRegistrationNo()).isEqualTo(UPDATED_REGISTRATION_NO);
    }

    @Test
    @Transactional
    public void updateNonExistingClinicOwnerInfo() throws Exception {
        int databaseSizeBeforeUpdate = clinicOwnerInfoRepository.findAll().size();

        // Create the ClinicOwnerInfo

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restClinicOwnerInfoMockMvc.perform(put("/api/clinic-owner-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clinicOwnerInfo)))
            .andExpect(status().isCreated());

        // Validate the ClinicOwnerInfo in the database
        List<ClinicOwnerInfo> clinicOwnerInfoList = clinicOwnerInfoRepository.findAll();
        assertThat(clinicOwnerInfoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteClinicOwnerInfo() throws Exception {
        // Initialize the database
        clinicOwnerInfoService.save(clinicOwnerInfo);

        int databaseSizeBeforeDelete = clinicOwnerInfoRepository.findAll().size();

        // Get the clinicOwnerInfo
        restClinicOwnerInfoMockMvc.perform(delete("/api/clinic-owner-infos/{id}", clinicOwnerInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ClinicOwnerInfo> clinicOwnerInfoList = clinicOwnerInfoRepository.findAll();
        assertThat(clinicOwnerInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClinicOwnerInfo.class);
        ClinicOwnerInfo clinicOwnerInfo1 = new ClinicOwnerInfo();
        clinicOwnerInfo1.setId(1L);
        ClinicOwnerInfo clinicOwnerInfo2 = new ClinicOwnerInfo();
        clinicOwnerInfo2.setId(clinicOwnerInfo1.getId());
        assertThat(clinicOwnerInfo1).isEqualTo(clinicOwnerInfo2);
        clinicOwnerInfo2.setId(2L);
        assertThat(clinicOwnerInfo1).isNotEqualTo(clinicOwnerInfo2);
        clinicOwnerInfo1.setId(null);
        assertThat(clinicOwnerInfo1).isNotEqualTo(clinicOwnerInfo2);
    }
}
