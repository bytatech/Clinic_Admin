package com.lxisoft.byta.web.rest;

import com.lxisoft.byta.ClinicLenusApp;

import com.lxisoft.byta.domain.NonGoverntment;
import com.lxisoft.byta.repository.NonGoverntmentRepository;
import com.lxisoft.byta.service.NonGoverntmentService;
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
 * Test class for the NonGoverntmentResource REST controller.
 *
 * @see NonGoverntmentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClinicLenusApp.class)
public class NonGoverntmentResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private NonGoverntmentRepository nonGoverntmentRepository;

    @Autowired
    private NonGoverntmentService nonGoverntmentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNonGoverntmentMockMvc;

    private NonGoverntment nonGoverntment;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        NonGoverntmentResource nonGoverntmentResource = new NonGoverntmentResource(nonGoverntmentService);
        this.restNonGoverntmentMockMvc = MockMvcBuilders.standaloneSetup(nonGoverntmentResource)
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
    public static NonGoverntment createEntity(EntityManager em) {
        NonGoverntment nonGoverntment = new NonGoverntment()
            .name(DEFAULT_NAME);
        return nonGoverntment;
    }

    @Before
    public void initTest() {
        nonGoverntment = createEntity(em);
    }

    @Test
    @Transactional
    public void createNonGoverntment() throws Exception {
        int databaseSizeBeforeCreate = nonGoverntmentRepository.findAll().size();

        // Create the NonGoverntment
        restNonGoverntmentMockMvc.perform(post("/api/non-governtments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nonGoverntment)))
            .andExpect(status().isCreated());

        // Validate the NonGoverntment in the database
        List<NonGoverntment> nonGoverntmentList = nonGoverntmentRepository.findAll();
        assertThat(nonGoverntmentList).hasSize(databaseSizeBeforeCreate + 1);
        NonGoverntment testNonGoverntment = nonGoverntmentList.get(nonGoverntmentList.size() - 1);
        assertThat(testNonGoverntment.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createNonGoverntmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nonGoverntmentRepository.findAll().size();

        // Create the NonGoverntment with an existing ID
        nonGoverntment.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNonGoverntmentMockMvc.perform(post("/api/non-governtments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nonGoverntment)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<NonGoverntment> nonGoverntmentList = nonGoverntmentRepository.findAll();
        assertThat(nonGoverntmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNonGoverntments() throws Exception {
        // Initialize the database
        nonGoverntmentRepository.saveAndFlush(nonGoverntment);

        // Get all the nonGoverntmentList
        restNonGoverntmentMockMvc.perform(get("/api/non-governtments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nonGoverntment.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getNonGoverntment() throws Exception {
        // Initialize the database
        nonGoverntmentRepository.saveAndFlush(nonGoverntment);

        // Get the nonGoverntment
        restNonGoverntmentMockMvc.perform(get("/api/non-governtments/{id}", nonGoverntment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nonGoverntment.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNonGoverntment() throws Exception {
        // Get the nonGoverntment
        restNonGoverntmentMockMvc.perform(get("/api/non-governtments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNonGoverntment() throws Exception {
        // Initialize the database
        nonGoverntmentService.save(nonGoverntment);

        int databaseSizeBeforeUpdate = nonGoverntmentRepository.findAll().size();

        // Update the nonGoverntment
        NonGoverntment updatedNonGoverntment = nonGoverntmentRepository.findOne(nonGoverntment.getId());
        updatedNonGoverntment
            .name(UPDATED_NAME);

        restNonGoverntmentMockMvc.perform(put("/api/non-governtments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNonGoverntment)))
            .andExpect(status().isOk());

        // Validate the NonGoverntment in the database
        List<NonGoverntment> nonGoverntmentList = nonGoverntmentRepository.findAll();
        assertThat(nonGoverntmentList).hasSize(databaseSizeBeforeUpdate);
        NonGoverntment testNonGoverntment = nonGoverntmentList.get(nonGoverntmentList.size() - 1);
        assertThat(testNonGoverntment.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingNonGoverntment() throws Exception {
        int databaseSizeBeforeUpdate = nonGoverntmentRepository.findAll().size();

        // Create the NonGoverntment

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNonGoverntmentMockMvc.perform(put("/api/non-governtments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nonGoverntment)))
            .andExpect(status().isCreated());

        // Validate the NonGoverntment in the database
        List<NonGoverntment> nonGoverntmentList = nonGoverntmentRepository.findAll();
        assertThat(nonGoverntmentList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteNonGoverntment() throws Exception {
        // Initialize the database
        nonGoverntmentService.save(nonGoverntment);

        int databaseSizeBeforeDelete = nonGoverntmentRepository.findAll().size();

        // Get the nonGoverntment
        restNonGoverntmentMockMvc.perform(delete("/api/non-governtments/{id}", nonGoverntment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NonGoverntment> nonGoverntmentList = nonGoverntmentRepository.findAll();
        assertThat(nonGoverntmentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NonGoverntment.class);
        NonGoverntment nonGoverntment1 = new NonGoverntment();
        nonGoverntment1.setId(1L);
        NonGoverntment nonGoverntment2 = new NonGoverntment();
        nonGoverntment2.setId(nonGoverntment1.getId());
        assertThat(nonGoverntment1).isEqualTo(nonGoverntment2);
        nonGoverntment2.setId(2L);
        assertThat(nonGoverntment1).isNotEqualTo(nonGoverntment2);
        nonGoverntment1.setId(null);
        assertThat(nonGoverntment1).isNotEqualTo(nonGoverntment2);
    }
}
