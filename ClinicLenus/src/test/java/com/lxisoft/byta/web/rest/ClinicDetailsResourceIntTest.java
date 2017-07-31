package com.lxisoft.byta.web.rest;

import com.lxisoft.byta.ClinicLenusApp;

import com.lxisoft.byta.domain.ClinicDetails;
import com.lxisoft.byta.repository.ClinicDetailsRepository;
import com.lxisoft.byta.service.ClinicDetailsService;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ClinicDetailsResource REST controller.
 *
 * @see ClinicDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClinicLenusApp.class)
public class ClinicDetailsResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Long DEFAULT_PINCODE = 1L;
    private static final Long UPDATED_PINCODE = 2L;

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final Long DEFAULT_MOBILE = 1L;
    private static final Long UPDATED_MOBILE = 2L;

    private static final LocalDate DEFAULT_CLINIC_TIMING = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CLINIC_TIMING = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_LANDMARKS = "AAAAAAAAAA";
    private static final String UPDATED_LANDMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_CLINIC_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_CLINIC_DESCRIPTION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_CLINIC_IMAGES = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CLINIC_IMAGES = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_CLINIC_IMAGES_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CLINIC_IMAGES_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    @Autowired
    private ClinicDetailsRepository clinicDetailsRepository;

    @Autowired
    private ClinicDetailsService clinicDetailsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restClinicDetailsMockMvc;

    private ClinicDetails clinicDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ClinicDetailsResource clinicDetailsResource = new ClinicDetailsResource(clinicDetailsService);
        this.restClinicDetailsMockMvc = MockMvcBuilders.standaloneSetup(clinicDetailsResource)
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
    public static ClinicDetails createEntity(EntityManager em) {
        ClinicDetails clinicDetails = new ClinicDetails()
            .name(DEFAULT_NAME)
            .address(DEFAULT_ADDRESS)
            .pincode(DEFAULT_PINCODE)
            .country(DEFAULT_COUNTRY)
            .mobile(DEFAULT_MOBILE)
            .clinicTiming(DEFAULT_CLINIC_TIMING)
            .category(DEFAULT_CATEGORY)
            .landmarks(DEFAULT_LANDMARKS)
            .website(DEFAULT_WEBSITE)
            .clinicDescription(DEFAULT_CLINIC_DESCRIPTION)
            .clinicImages(DEFAULT_CLINIC_IMAGES)
            .clinicImagesContentType(DEFAULT_CLINIC_IMAGES_CONTENT_TYPE)
            .location(DEFAULT_LOCATION);
        return clinicDetails;
    }

    @Before
    public void initTest() {
        clinicDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createClinicDetails() throws Exception {
        int databaseSizeBeforeCreate = clinicDetailsRepository.findAll().size();

        // Create the ClinicDetails
        restClinicDetailsMockMvc.perform(post("/api/clinic-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clinicDetails)))
            .andExpect(status().isCreated());

        // Validate the ClinicDetails in the database
        List<ClinicDetails> clinicDetailsList = clinicDetailsRepository.findAll();
        assertThat(clinicDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        ClinicDetails testClinicDetails = clinicDetailsList.get(clinicDetailsList.size() - 1);
        assertThat(testClinicDetails.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testClinicDetails.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testClinicDetails.getPincode()).isEqualTo(DEFAULT_PINCODE);
        assertThat(testClinicDetails.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testClinicDetails.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testClinicDetails.getClinicTiming()).isEqualTo(DEFAULT_CLINIC_TIMING);
        assertThat(testClinicDetails.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testClinicDetails.getLandmarks()).isEqualTo(DEFAULT_LANDMARKS);
        assertThat(testClinicDetails.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testClinicDetails.getClinicDescription()).isEqualTo(DEFAULT_CLINIC_DESCRIPTION);
        assertThat(testClinicDetails.getClinicImages()).isEqualTo(DEFAULT_CLINIC_IMAGES);
        assertThat(testClinicDetails.getClinicImagesContentType()).isEqualTo(DEFAULT_CLINIC_IMAGES_CONTENT_TYPE);
        assertThat(testClinicDetails.getLocation()).isEqualTo(DEFAULT_LOCATION);
    }

    @Test
    @Transactional
    public void createClinicDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = clinicDetailsRepository.findAll().size();

        // Create the ClinicDetails with an existing ID
        clinicDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClinicDetailsMockMvc.perform(post("/api/clinic-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clinicDetails)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ClinicDetails> clinicDetailsList = clinicDetailsRepository.findAll();
        assertThat(clinicDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllClinicDetails() throws Exception {
        // Initialize the database
        clinicDetailsRepository.saveAndFlush(clinicDetails);

        // Get all the clinicDetailsList
        restClinicDetailsMockMvc.perform(get("/api/clinic-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clinicDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE.intValue())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.intValue())))
            .andExpect(jsonPath("$.[*].clinicTiming").value(hasItem(DEFAULT_CLINIC_TIMING.toString())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].landmarks").value(hasItem(DEFAULT_LANDMARKS.toString())))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE.toString())))
            .andExpect(jsonPath("$.[*].clinicDescription").value(hasItem(DEFAULT_CLINIC_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].clinicImagesContentType").value(hasItem(DEFAULT_CLINIC_IMAGES_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].clinicImages").value(hasItem(Base64Utils.encodeToString(DEFAULT_CLINIC_IMAGES))))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION.toString())));
    }

    @Test
    @Transactional
    public void getClinicDetails() throws Exception {
        // Initialize the database
        clinicDetailsRepository.saveAndFlush(clinicDetails);

        // Get the clinicDetails
        restClinicDetailsMockMvc.perform(get("/api/clinic-details/{id}", clinicDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(clinicDetails.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.pincode").value(DEFAULT_PINCODE.intValue()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.intValue()))
            .andExpect(jsonPath("$.clinicTiming").value(DEFAULT_CLINIC_TIMING.toString()))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY.toString()))
            .andExpect(jsonPath("$.landmarks").value(DEFAULT_LANDMARKS.toString()))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE.toString()))
            .andExpect(jsonPath("$.clinicDescription").value(DEFAULT_CLINIC_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.clinicImagesContentType").value(DEFAULT_CLINIC_IMAGES_CONTENT_TYPE))
            .andExpect(jsonPath("$.clinicImages").value(Base64Utils.encodeToString(DEFAULT_CLINIC_IMAGES)))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingClinicDetails() throws Exception {
        // Get the clinicDetails
        restClinicDetailsMockMvc.perform(get("/api/clinic-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClinicDetails() throws Exception {
        // Initialize the database
        clinicDetailsService.save(clinicDetails);

        int databaseSizeBeforeUpdate = clinicDetailsRepository.findAll().size();

        // Update the clinicDetails
        ClinicDetails updatedClinicDetails = clinicDetailsRepository.findOne(clinicDetails.getId());
        updatedClinicDetails
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .pincode(UPDATED_PINCODE)
            .country(UPDATED_COUNTRY)
            .mobile(UPDATED_MOBILE)
            .clinicTiming(UPDATED_CLINIC_TIMING)
            .category(UPDATED_CATEGORY)
            .landmarks(UPDATED_LANDMARKS)
            .website(UPDATED_WEBSITE)
            .clinicDescription(UPDATED_CLINIC_DESCRIPTION)
            .clinicImages(UPDATED_CLINIC_IMAGES)
            .clinicImagesContentType(UPDATED_CLINIC_IMAGES_CONTENT_TYPE)
            .location(UPDATED_LOCATION);

        restClinicDetailsMockMvc.perform(put("/api/clinic-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedClinicDetails)))
            .andExpect(status().isOk());

        // Validate the ClinicDetails in the database
        List<ClinicDetails> clinicDetailsList = clinicDetailsRepository.findAll();
        assertThat(clinicDetailsList).hasSize(databaseSizeBeforeUpdate);
        ClinicDetails testClinicDetails = clinicDetailsList.get(clinicDetailsList.size() - 1);
        assertThat(testClinicDetails.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testClinicDetails.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testClinicDetails.getPincode()).isEqualTo(UPDATED_PINCODE);
        assertThat(testClinicDetails.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testClinicDetails.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testClinicDetails.getClinicTiming()).isEqualTo(UPDATED_CLINIC_TIMING);
        assertThat(testClinicDetails.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testClinicDetails.getLandmarks()).isEqualTo(UPDATED_LANDMARKS);
        assertThat(testClinicDetails.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testClinicDetails.getClinicDescription()).isEqualTo(UPDATED_CLINIC_DESCRIPTION);
        assertThat(testClinicDetails.getClinicImages()).isEqualTo(UPDATED_CLINIC_IMAGES);
        assertThat(testClinicDetails.getClinicImagesContentType()).isEqualTo(UPDATED_CLINIC_IMAGES_CONTENT_TYPE);
        assertThat(testClinicDetails.getLocation()).isEqualTo(UPDATED_LOCATION);
    }

    @Test
    @Transactional
    public void updateNonExistingClinicDetails() throws Exception {
        int databaseSizeBeforeUpdate = clinicDetailsRepository.findAll().size();

        // Create the ClinicDetails

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restClinicDetailsMockMvc.perform(put("/api/clinic-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clinicDetails)))
            .andExpect(status().isCreated());

        // Validate the ClinicDetails in the database
        List<ClinicDetails> clinicDetailsList = clinicDetailsRepository.findAll();
        assertThat(clinicDetailsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteClinicDetails() throws Exception {
        // Initialize the database
        clinicDetailsService.save(clinicDetails);

        int databaseSizeBeforeDelete = clinicDetailsRepository.findAll().size();

        // Get the clinicDetails
        restClinicDetailsMockMvc.perform(delete("/api/clinic-details/{id}", clinicDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ClinicDetails> clinicDetailsList = clinicDetailsRepository.findAll();
        assertThat(clinicDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClinicDetails.class);
        ClinicDetails clinicDetails1 = new ClinicDetails();
        clinicDetails1.setId(1L);
        ClinicDetails clinicDetails2 = new ClinicDetails();
        clinicDetails2.setId(clinicDetails1.getId());
        assertThat(clinicDetails1).isEqualTo(clinicDetails2);
        clinicDetails2.setId(2L);
        assertThat(clinicDetails1).isNotEqualTo(clinicDetails2);
        clinicDetails1.setId(null);
        assertThat(clinicDetails1).isNotEqualTo(clinicDetails2);
    }
}
