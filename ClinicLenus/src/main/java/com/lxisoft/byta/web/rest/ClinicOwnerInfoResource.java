package com.lxisoft.byta.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.byta.domain.ClinicOwnerInfo;
import com.lxisoft.byta.service.ClinicOwnerInfoService;
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
 * REST controller for managing ClinicOwnerInfo.
 */
@RestController
@RequestMapping("/api")
public class ClinicOwnerInfoResource {

    private final Logger log = LoggerFactory.getLogger(ClinicOwnerInfoResource.class);

    private static final String ENTITY_NAME = "clinicOwnerInfo";

    private final ClinicOwnerInfoService clinicOwnerInfoService;

    public ClinicOwnerInfoResource(ClinicOwnerInfoService clinicOwnerInfoService) {
        this.clinicOwnerInfoService = clinicOwnerInfoService;
    }

    /**
     * POST  /clinic-owner-infos : Create a new clinicOwnerInfo.
     *
     * @param clinicOwnerInfo the clinicOwnerInfo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new clinicOwnerInfo, or with status 400 (Bad Request) if the clinicOwnerInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/clinic-owner-infos")
    @Timed
    public ResponseEntity<ClinicOwnerInfo> createClinicOwnerInfo(@RequestBody ClinicOwnerInfo clinicOwnerInfo) throws URISyntaxException {
        log.debug("REST request to save ClinicOwnerInfo : {}", clinicOwnerInfo);
        if (clinicOwnerInfo.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new clinicOwnerInfo cannot already have an ID")).body(null);
        }
        ClinicOwnerInfo result = clinicOwnerInfoService.save(clinicOwnerInfo);
        return ResponseEntity.created(new URI("/api/clinic-owner-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /clinic-owner-infos : Updates an existing clinicOwnerInfo.
     *
     * @param clinicOwnerInfo the clinicOwnerInfo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated clinicOwnerInfo,
     * or with status 400 (Bad Request) if the clinicOwnerInfo is not valid,
     * or with status 500 (Internal Server Error) if the clinicOwnerInfo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/clinic-owner-infos")
    @Timed
    public ResponseEntity<ClinicOwnerInfo> updateClinicOwnerInfo(@RequestBody ClinicOwnerInfo clinicOwnerInfo) throws URISyntaxException {
        log.debug("REST request to update ClinicOwnerInfo : {}", clinicOwnerInfo);
        if (clinicOwnerInfo.getId() == null) {
            return createClinicOwnerInfo(clinicOwnerInfo);
        }
        ClinicOwnerInfo result = clinicOwnerInfoService.save(clinicOwnerInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, clinicOwnerInfo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /clinic-owner-infos : get all the clinicOwnerInfos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of clinicOwnerInfos in body
     */
    @GetMapping("/clinic-owner-infos")
    @Timed
    public List<ClinicOwnerInfo> getAllClinicOwnerInfos() {
        log.debug("REST request to get all ClinicOwnerInfos");
        return clinicOwnerInfoService.findAll();
    }

    /**
     * GET  /clinic-owner-infos/:id : get the "id" clinicOwnerInfo.
     *
     * @param id the id of the clinicOwnerInfo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the clinicOwnerInfo, or with status 404 (Not Found)
     */
    @GetMapping("/clinic-owner-infos/{id}")
    @Timed
    public ResponseEntity<ClinicOwnerInfo> getClinicOwnerInfo(@PathVariable Long id) {
        log.debug("REST request to get ClinicOwnerInfo : {}", id);
        ClinicOwnerInfo clinicOwnerInfo = clinicOwnerInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(clinicOwnerInfo));
    }

    /**
     * DELETE  /clinic-owner-infos/:id : delete the "id" clinicOwnerInfo.
     *
     * @param id the id of the clinicOwnerInfo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/clinic-owner-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteClinicOwnerInfo(@PathVariable Long id) {
        log.debug("REST request to delete ClinicOwnerInfo : {}", id);
        clinicOwnerInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
