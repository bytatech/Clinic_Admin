package com.lxisoft.byta.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.byta.domain.NonGoverntment;
import com.lxisoft.byta.service.NonGoverntmentService;
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
 * REST controller for managing NonGoverntment.
 */
@RestController
@RequestMapping("/api")
public class NonGoverntmentResource {

    private final Logger log = LoggerFactory.getLogger(NonGoverntmentResource.class);

    private static final String ENTITY_NAME = "nonGoverntment";

    private final NonGoverntmentService nonGoverntmentService;

    public NonGoverntmentResource(NonGoverntmentService nonGoverntmentService) {
        this.nonGoverntmentService = nonGoverntmentService;
    }

    /**
     * POST  /non-governtments : Create a new nonGoverntment.
     *
     * @param nonGoverntment the nonGoverntment to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nonGoverntment, or with status 400 (Bad Request) if the nonGoverntment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/non-governtments")
    @Timed
    public ResponseEntity<NonGoverntment> createNonGoverntment(@RequestBody NonGoverntment nonGoverntment) throws URISyntaxException {
        log.debug("REST request to save NonGoverntment : {}", nonGoverntment);
        if (nonGoverntment.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new nonGoverntment cannot already have an ID")).body(null);
        }
        NonGoverntment result = nonGoverntmentService.save(nonGoverntment);
        return ResponseEntity.created(new URI("/api/non-governtments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /non-governtments : Updates an existing nonGoverntment.
     *
     * @param nonGoverntment the nonGoverntment to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nonGoverntment,
     * or with status 400 (Bad Request) if the nonGoverntment is not valid,
     * or with status 500 (Internal Server Error) if the nonGoverntment couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/non-governtments")
    @Timed
    public ResponseEntity<NonGoverntment> updateNonGoverntment(@RequestBody NonGoverntment nonGoverntment) throws URISyntaxException {
        log.debug("REST request to update NonGoverntment : {}", nonGoverntment);
        if (nonGoverntment.getId() == null) {
            return createNonGoverntment(nonGoverntment);
        }
        NonGoverntment result = nonGoverntmentService.save(nonGoverntment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nonGoverntment.getId().toString()))
            .body(result);
    }

    /**
     * GET  /non-governtments : get all the nonGoverntments.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of nonGoverntments in body
     */
    @GetMapping("/non-governtments")
    @Timed
    public List<NonGoverntment> getAllNonGoverntments() {
        log.debug("REST request to get all NonGoverntments");
        return nonGoverntmentService.findAll();
    }

    /**
     * GET  /non-governtments/:id : get the "id" nonGoverntment.
     *
     * @param id the id of the nonGoverntment to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nonGoverntment, or with status 404 (Not Found)
     */
    @GetMapping("/non-governtments/{id}")
    @Timed
    public ResponseEntity<NonGoverntment> getNonGoverntment(@PathVariable Long id) {
        log.debug("REST request to get NonGoverntment : {}", id);
        NonGoverntment nonGoverntment = nonGoverntmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(nonGoverntment));
    }

    /**
     * DELETE  /non-governtments/:id : delete the "id" nonGoverntment.
     *
     * @param id the id of the nonGoverntment to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/non-governtments/{id}")
    @Timed
    public ResponseEntity<Void> deleteNonGoverntment(@PathVariable Long id) {
        log.debug("REST request to delete NonGoverntment : {}", id);
        nonGoverntmentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
