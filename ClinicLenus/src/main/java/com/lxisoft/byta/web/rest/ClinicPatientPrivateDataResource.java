package com.lxisoft.byta.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.byta.domain.ClinicPatientPrivateData;
import com.lxisoft.byta.service.ClinicPatientPrivateDataService;
import com.lxisoft.byta.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ClinicPatientPrivateData.
 */
@RestController
@RequestMapping("/api")
public class ClinicPatientPrivateDataResource {

    private final Logger log = LoggerFactory.getLogger(ClinicPatientPrivateDataResource.class);

    private static final String ENTITY_NAME = "clinicPatientPrivateData";

    private final ClinicPatientPrivateDataService clinicPatientPrivateDataService;

    public ClinicPatientPrivateDataResource(ClinicPatientPrivateDataService clinicPatientPrivateDataService) {
        this.clinicPatientPrivateDataService = clinicPatientPrivateDataService;
    }

    /**
     * POST  /clinic-patient-private-data : Create a new clinicPatientPrivateData.
     *
     * @param clinicPatientPrivateData the clinicPatientPrivateData to create
     * @return the ResponseEntity with status 201 (Created) and with body the new clinicPatientPrivateData, or with status 400 (Bad Request) if the clinicPatientPrivateData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/clinic-patient-private-data")
    @Timed
    public ResponseEntity<ClinicPatientPrivateData> createClinicPatientPrivateData(@RequestBody ClinicPatientPrivateData clinicPatientPrivateData) throws URISyntaxException {
        log.debug("REST request to save ClinicPatientPrivateData : {}", clinicPatientPrivateData);
        if (clinicPatientPrivateData.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new clinicPatientPrivateData cannot already have an ID")).body(null);
        }
        ClinicPatientPrivateData result = clinicPatientPrivateDataService.save(clinicPatientPrivateData);
        return ResponseEntity.created(new URI("/api/clinic-patient-private-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /clinic-patient-private-data : Updates an existing clinicPatientPrivateData.
     *
     * @param clinicPatientPrivateData the clinicPatientPrivateData to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated clinicPatientPrivateData,
     * or with status 400 (Bad Request) if the clinicPatientPrivateData is not valid,
     * or with status 500 (Internal Server Error) if the clinicPatientPrivateData couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/clinic-patient-private-data")
    @Timed
    public ResponseEntity<ClinicPatientPrivateData> updateClinicPatientPrivateData(@RequestBody ClinicPatientPrivateData clinicPatientPrivateData) throws URISyntaxException {
        log.debug("REST request to update ClinicPatientPrivateData : {}", clinicPatientPrivateData);
        if (clinicPatientPrivateData.getId() == null) {
            return createClinicPatientPrivateData(clinicPatientPrivateData);
        }
        ClinicPatientPrivateData result = clinicPatientPrivateDataService.save(clinicPatientPrivateData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, clinicPatientPrivateData.getId().toString()))
            .body(result);
    }

    /**
     * GET  /clinic-patient-private-data : get all the clinicPatientPrivateData.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of clinicPatientPrivateData in body
     */
    @GetMapping("/clinic-patient-private-data")
    @Timed
    public List<ClinicPatientPrivateData> getAllClinicPatientPrivateData() {
        log.debug("REST request to get all ClinicPatientPrivateData");
        return clinicPatientPrivateDataService.findAll();
    }

    /**
     * GET  /clinic-patient-private-data/:id : get the "id" clinicPatientPrivateData.
     *
     * @param id the id of the clinicPatientPrivateData to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the clinicPatientPrivateData, or with status 404 (Not Found)
     */
    @GetMapping("/clinic-patient-private-data/{id}")
    @Timed
    public ResponseEntity<ClinicPatientPrivateData> getClinicPatientPrivateData(@PathVariable Long id) {
        log.debug("REST request to get ClinicPatientPrivateData : {}", id);
        ClinicPatientPrivateData clinicPatientPrivateData = clinicPatientPrivateDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(clinicPatientPrivateData));
    }

    /**
     * DELETE  /clinic-patient-private-data/:id : delete the "id" clinicPatientPrivateData.
     *
     * @param id the id of the clinicPatientPrivateData to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/clinic-patient-private-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteClinicPatientPrivateData(@PathVariable Long id) {
        log.debug("REST request to delete ClinicPatientPrivateData : {}", id);
        clinicPatientPrivateDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
