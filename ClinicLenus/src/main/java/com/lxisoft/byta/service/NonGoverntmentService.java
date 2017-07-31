package com.lxisoft.byta.service;

import com.lxisoft.byta.domain.NonGoverntment;
import java.util.List;

/**
 * Service Interface for managing NonGoverntment.
 */
public interface NonGoverntmentService {

    /**
     * Save a nonGoverntment.
     *
     * @param nonGoverntment the entity to save
     * @return the persisted entity
     */
    NonGoverntment save(NonGoverntment nonGoverntment);

    /**
     *  Get all the nonGoverntments.
     *
     *  @return the list of entities
     */
    List<NonGoverntment> findAll();

    /**
     *  Get the "id" nonGoverntment.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    NonGoverntment findOne(Long id);

    /**
     *  Delete the "id" nonGoverntment.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
