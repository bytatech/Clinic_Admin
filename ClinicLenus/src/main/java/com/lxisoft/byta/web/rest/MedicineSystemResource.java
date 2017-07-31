package com.lxisoft.byta.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.byta.domain.MedicineSystem;
import com.lxisoft.byta.service.MedicineSystemService;
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
 * REST controller for managing MedicineSystem.
 */
@RestController
@RequestMapping("/api")
public class MedicineSystemResource {

    private final Logger log = LoggerFactory.getLogger(MedicineSystemResource.class);

    private static final String ENTITY_NAME = "medicineSystem";

    private final MedicineSystemService medicineSystemService;

    public MedicineSystemResource(MedicineSystemService medicineSystemService) {
        this.medicineSystemService = medicineSystemService;
    }

    /**
     * POST  /medicine-systems : Create a new medicineSystem.
     *
     * @param medicineSystem the medicineSystem to create
     * @return the ResponseEntity with status 201 (Created) and with body the new medicineSystem, or with status 400 (Bad Request) if the medicineSystem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/medicine-systems")
    @Timed
    public ResponseEntity<MedicineSystem> createMedicineSystem(@RequestBody MedicineSystem medicineSystem) throws URISyntaxException {
        log.debug("REST request to save MedicineSystem : {}", medicineSystem);
        if (medicineSystem.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new medicineSystem cannot already have an ID")).body(null);
        }
        MedicineSystem result = medicineSystemService.save(medicineSystem);
        return ResponseEntity.created(new URI("/api/medicine-systems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /medicine-systems : Updates an existing medicineSystem.
     *
     * @param medicineSystem the medicineSystem to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated medicineSystem,
     * or with status 400 (Bad Request) if the medicineSystem is not valid,
     * or with status 500 (Internal Server Error) if the medicineSystem couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/medicine-systems")
    @Timed
    public ResponseEntity<MedicineSystem> updateMedicineSystem(@RequestBody MedicineSystem medicineSystem) throws URISyntaxException {
        log.debug("REST request to update MedicineSystem : {}", medicineSystem);
        if (medicineSystem.getId() == null) {
            return createMedicineSystem(medicineSystem);
        }
        MedicineSystem result = medicineSystemService.save(medicineSystem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, medicineSystem.getId().toString()))
            .body(result);
    }

    /**
     * GET  /medicine-systems : get all the medicineSystems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of medicineSystems in body
     */
    @GetMapping("/medicine-systems")
    @Timed
    public List<MedicineSystem> getAllMedicineSystems() {
        log.debug("REST request to get all MedicineSystems");
        return medicineSystemService.findAll();
    }

    /**
     * GET  /medicine-systems/:id : get the "id" medicineSystem.
     *
     * @param id the id of the medicineSystem to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the medicineSystem, or with status 404 (Not Found)
     */
    @GetMapping("/medicine-systems/{id}")
    @Timed
    public ResponseEntity<MedicineSystem> getMedicineSystem(@PathVariable Long id) {
        log.debug("REST request to get MedicineSystem : {}", id);
        MedicineSystem medicineSystem = medicineSystemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(medicineSystem));
    }

    /**
     * DELETE  /medicine-systems/:id : delete the "id" medicineSystem.
     *
     * @param id the id of the medicineSystem to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/medicine-systems/{id}")
    @Timed
    public ResponseEntity<Void> deleteMedicineSystem(@PathVariable Long id) {
        log.debug("REST request to delete MedicineSystem : {}", id);
        medicineSystemService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
