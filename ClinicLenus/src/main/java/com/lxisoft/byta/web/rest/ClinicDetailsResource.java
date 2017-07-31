package com.lxisoft.byta.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.byta.domain.ClinicDetails;
import com.lxisoft.byta.service.ClinicDetailsService;
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
 * REST controller for managing ClinicDetails.
 */
@RestController
@RequestMapping("/api")
public class ClinicDetailsResource {

    private final Logger log = LoggerFactory.getLogger(ClinicDetailsResource.class);

    private static final String ENTITY_NAME = "clinicDetails";

    private final ClinicDetailsService clinicDetailsService;

    public ClinicDetailsResource(ClinicDetailsService clinicDetailsService) {
        this.clinicDetailsService = clinicDetailsService;
    }

    /**
     * POST  /clinic-details : Create a new clinicDetails.
     *
     * @param clinicDetails the clinicDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new clinicDetails, or with status 400 (Bad Request) if the clinicDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/clinic-details")
    @Timed
    public ResponseEntity<ClinicDetails> createClinicDetails(@RequestBody ClinicDetails clinicDetails) throws URISyntaxException {
        log.debug("REST request to save ClinicDetails : {}", clinicDetails);
        if (clinicDetails.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new clinicDetails cannot already have an ID")).body(null);
        }
        ClinicDetails result = clinicDetailsService.save(clinicDetails);
        return ResponseEntity.created(new URI("/api/clinic-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /clinic-details : Updates an existing clinicDetails.
     *
     * @param clinicDetails the clinicDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated clinicDetails,
     * or with status 400 (Bad Request) if the clinicDetails is not valid,
     * or with status 500 (Internal Server Error) if the clinicDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/clinic-details")
    @Timed
    public ResponseEntity<ClinicDetails> updateClinicDetails(@RequestBody ClinicDetails clinicDetails) throws URISyntaxException {
        log.debug("REST request to update ClinicDetails : {}", clinicDetails);
        if (clinicDetails.getId() == null) {
            return createClinicDetails(clinicDetails);
        }
        ClinicDetails result = clinicDetailsService.save(clinicDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, clinicDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /clinic-details : get all the clinicDetails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of clinicDetails in body
     */
    @GetMapping("/clinic-details")
    @Timed
    public List<ClinicDetails> getAllClinicDetails() {
        log.debug("REST request to get all ClinicDetails");
        return clinicDetailsService.findAll();
    }

    /**
     * GET  /clinic-details/:id : get the "id" clinicDetails.
     *
     * @param id the id of the clinicDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the clinicDetails, or with status 404 (Not Found)
     */
    @GetMapping("/clinic-details/{id}")
    @Timed
    public ResponseEntity<ClinicDetails> getClinicDetails(@PathVariable Long id) {
        log.debug("REST request to get ClinicDetails : {}", id);
        ClinicDetails clinicDetails = clinicDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(clinicDetails));
    }

    /**
     * DELETE  /clinic-details/:id : delete the "id" clinicDetails.
     *
     * @param id the id of the clinicDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/clinic-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteClinicDetails(@PathVariable Long id) {
        log.debug("REST request to delete ClinicDetails : {}", id);
        clinicDetailsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
