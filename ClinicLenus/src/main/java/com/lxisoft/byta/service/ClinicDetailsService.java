package com.lxisoft.byta.service;

import com.lxisoft.byta.domain.ClinicDetails;
import java.util.List;

/**
 * Service Interface for managing ClinicDetails.
 */
public interface ClinicDetailsService {

    /**
     * Save a clinicDetails.
     *
     * @param clinicDetails the entity to save
     * @return the persisted entity
     */
    ClinicDetails save(ClinicDetails clinicDetails);

    /**
     *  Get all the clinicDetails.
     *
     *  @return the list of entities
     */
    List<ClinicDetails> findAll();

    /**
     *  Get the "id" clinicDetails.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ClinicDetails findOne(Long id);

    /**
     *  Delete the "id" clinicDetails.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
