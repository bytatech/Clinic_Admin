package com.lxisoft.byta.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.byta.domain.ClinicPatientData;
import com.lxisoft.byta.service.ClinicPatientDataService;
import com.lxisoft.byta.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ClinicPatientData.
 */
@RestController
@RequestMapping("/api")
public class ClinicPatientDataResource {

    private final Logger log = LoggerFactory.getLogger(ClinicPatientDataResource.class);

    private static final String ENTITY_NAME = "clinicPatientData";

    private final ClinicPatientDataService clinicPatientDataService;

    public ClinicPatientDataResource(ClinicPatientDataService clinicPatientDataService) {
        this.clinicPatientDataService = clinicPatientDataService;
    }

    /**
     * POST  /clinic-patient-data : Create a new clinicPatientData.
     *
     * @param clinicPatientData the clinicPatientData to create
     * @return the ResponseEntity with status 201 (Created) and with body the new clinicPatientData, or with status 400 (Bad Request) if the clinicPatientData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/clinic-patient-data")
    @Timed
    public ResponseEntity<ClinicPatientData> createClinicPatientData(@Valid @RequestBody ClinicPatientData clinicPatientData) throws URISyntaxException {
        log.debug("REST request to save ClinicPatientData : {}", clinicPatientData);
        if (clinicPatientData.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new clinicPatientData cannot already have an ID")).body(null);
        }
        ClinicPatientData result = clinicPatientDataService.save(clinicPatientData);
        return ResponseEntity.created(new URI("/api/clinic-patient-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /clinic-patient-data : Updates an existing clinicPatientData.
     *
     * @param clinicPatientData the clinicPatientData to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated clinicPatientData,
     * or with status 400 (Bad Request) if the clinicPatientData is not valid,
     * or with status 500 (Internal Server Error) if the clinicPatientData couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/clinic-patient-data")
    @Timed
    public ResponseEntity<ClinicPatientData> updateClinicPatientData(@Valid @RequestBody ClinicPatientData clinicPatientData) throws URISyntaxException {
        log.debug("REST request to update ClinicPatientData : {}", clinicPatientData);
        if (clinicPatientData.getId() == null) {
            return createClinicPatientData(clinicPatientData);
        }
        ClinicPatientData result = clinicPatientDataService.save(clinicPatientData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, clinicPatientData.getId().toString()))
            .body(result);
    }

    /**
     * GET  /clinic-patient-data : get all the clinicPatientData.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of clinicPatientData in body
     */
    @GetMapping("/clinic-patient-data")
    @Timed
    public List<ClinicPatientData> getAllClinicPatientData() {
        log.debug("REST request to get all ClinicPatientData");
        return clinicPatientDataService.findAll();
    }

    /**
     * GET  /clinic-patient-data/:id : get the "id" clinicPatientData.
     *
     * @param id the id of the clinicPatientData to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the clinicPatientData, or with status 404 (Not Found)
     */
    @GetMapping("/clinic-patient-data/{id}")
    @Timed
    public ResponseEntity<ClinicPatientData> getClinicPatientData(@PathVariable Long id) {
        log.debug("REST request to get ClinicPatientData : {}", id);
        ClinicPatientData clinicPatientData = clinicPatientDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(clinicPatientData));
    }

    /**
     * DELETE  /clinic-patient-data/:id : delete the "id" clinicPatientData.
     *
     * @param id the id of the clinicPatientData to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/clinic-patient-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteClinicPatientData(@PathVariable Long id) {
        log.debug("REST request to delete ClinicPatientData : {}", id);
        clinicPatientDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
