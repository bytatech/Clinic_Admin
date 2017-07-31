package com.lxisoft.byta.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.byta.domain.Governtment;
import com.lxisoft.byta.service.GoverntmentService;
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
 * REST controller for managing Governtment.
 */
@RestController
@RequestMapping("/api")
public class GoverntmentResource {

    private final Logger log = LoggerFactory.getLogger(GoverntmentResource.class);

    private static final String ENTITY_NAME = "governtment";

    private final GoverntmentService governtmentService;

    public GoverntmentResource(GoverntmentService governtmentService) {
        this.governtmentService = governtmentService;
    }

    /**
     * POST  /governtments : Create a new governtment.
     *
     * @param governtment the governtment to create
     * @return the ResponseEntity with status 201 (Created) and with body the new governtment, or with status 400 (Bad Request) if the governtment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/governtments")
    @Timed
    public ResponseEntity<Governtment> createGoverntment(@RequestBody Governtment governtment) throws URISyntaxException {
        log.debug("REST request to save Governtment : {}", governtment);
        if (governtment.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new governtment cannot already have an ID")).body(null);
        }
        Governtment result = governtmentService.save(governtment);
        return ResponseEntity.created(new URI("/api/governtments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /governtments : Updates an existing governtment.
     *
     * @param governtment the governtment to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated governtment,
     * or with status 400 (Bad Request) if the governtment is not valid,
     * or with status 500 (Internal Server Error) if the governtment couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/governtments")
    @Timed
    public ResponseEntity<Governtment> updateGoverntment(@RequestBody Governtment governtment) throws URISyntaxException {
        log.debug("REST request to update Governtment : {}", governtment);
        if (governtment.getId() == null) {
            return createGoverntment(governtment);
        }
        Governtment result = governtmentService.save(governtment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, governtment.getId().toString()))
            .body(result);
    }

    /**
     * GET  /governtments : get all the governtments.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of governtments in body
     */
    @GetMapping("/governtments")
    @Timed
    public List<Governtment> getAllGoverntments() {
        log.debug("REST request to get all Governtments");
        return governtmentService.findAll();
    }

    /**
     * GET  /governtments/:id : get the "id" governtment.
     *
     * @param id the id of the governtment to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the governtment, or with status 404 (Not Found)
     */
    @GetMapping("/governtments/{id}")
    @Timed
    public ResponseEntity<Governtment> getGoverntment(@PathVariable Long id) {
        log.debug("REST request to get Governtment : {}", id);
        Governtment governtment = governtmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(governtment));
    }

    /**
     * DELETE  /governtments/:id : delete the "id" governtment.
     *
     * @param id the id of the governtment to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/governtments/{id}")
    @Timed
    public ResponseEntity<Void> deleteGoverntment(@PathVariable Long id) {
        log.debug("REST request to delete Governtment : {}", id);
        governtmentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
