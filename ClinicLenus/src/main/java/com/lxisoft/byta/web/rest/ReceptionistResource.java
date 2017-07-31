package com.lxisoft.byta.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.byta.domain.Receptionist;
import com.lxisoft.byta.service.ReceptionistService;
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
 * REST controller for managing Receptionist.
 */
@RestController
@RequestMapping("/api")
public class ReceptionistResource {

    private final Logger log = LoggerFactory.getLogger(ReceptionistResource.class);

    private static final String ENTITY_NAME = "receptionist";

    private final ReceptionistService receptionistService;

    public ReceptionistResource(ReceptionistService receptionistService) {
        this.receptionistService = receptionistService;
    }

    /**
     * POST  /receptionists : Create a new receptionist.
     *
     * @param receptionist the receptionist to create
     * @return the ResponseEntity with status 201 (Created) and with body the new receptionist, or with status 400 (Bad Request) if the receptionist has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/receptionists")
    @Timed
    public ResponseEntity<Receptionist> createReceptionist(@RequestBody Receptionist receptionist) throws URISyntaxException {
        log.debug("REST request to save Receptionist : {}", receptionist);
        if (receptionist.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new receptionist cannot already have an ID")).body(null);
        }
        Receptionist result = receptionistService.save(receptionist);
        return ResponseEntity.created(new URI("/api/receptionists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /receptionists : Updates an existing receptionist.
     *
     * @param receptionist the receptionist to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated receptionist,
     * or with status 400 (Bad Request) if the receptionist is not valid,
     * or with status 500 (Internal Server Error) if the receptionist couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/receptionists")
    @Timed
    public ResponseEntity<Receptionist> updateReceptionist(@RequestBody Receptionist receptionist) throws URISyntaxException {
        log.debug("REST request to update Receptionist : {}", receptionist);
        if (receptionist.getId() == null) {
            return createReceptionist(receptionist);
        }
        Receptionist result = receptionistService.save(receptionist);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, receptionist.getId().toString()))
            .body(result);
    }

    /**
     * GET  /receptionists : get all the receptionists.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of receptionists in body
     */
    @GetMapping("/receptionists")
    @Timed
    public List<Receptionist> getAllReceptionists() {
        log.debug("REST request to get all Receptionists");
        return receptionistService.findAll();
    }

    /**
     * GET  /receptionists/:id : get the "id" receptionist.
     *
     * @param id the id of the receptionist to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the receptionist, or with status 404 (Not Found)
     */
    @GetMapping("/receptionists/{id}")
    @Timed
    public ResponseEntity<Receptionist> getReceptionist(@PathVariable Long id) {
        log.debug("REST request to get Receptionist : {}", id);
        Receptionist receptionist = receptionistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(receptionist));
    }

    /**
     * DELETE  /receptionists/:id : delete the "id" receptionist.
     *
     * @param id the id of the receptionist to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/receptionists/{id}")
    @Timed
    public ResponseEntity<Void> deleteReceptionist(@PathVariable Long id) {
        log.debug("REST request to delete Receptionist : {}", id);
        receptionistService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
