package com.lxisoft.byta.service;

import com.lxisoft.byta.domain.Clinic;
import java.util.List;

/**
 * Service Interface for managing Clinic.
 */
public interface ClinicService {

    /**
     * Save a clinic.
     *
     * @param clinic the entity to save
     * @return the persisted entity
     */
    Clinic save(Clinic clinic);

    /**
     *  Get all the clinics.
     *
     *  @return the list of entities
     */
    List<Clinic> findAll();

    /**
     *  Get the "id" clinic.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Clinic findOne(Long id);

    /**
     *  Delete the "id" clinic.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
