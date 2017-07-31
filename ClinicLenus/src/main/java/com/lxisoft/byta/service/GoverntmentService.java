package com.lxisoft.byta.service;

import com.lxisoft.byta.domain.Governtment;
import java.util.List;

/**
 * Service Interface for managing Governtment.
 */
public interface GoverntmentService {

    /**
     * Save a governtment.
     *
     * @param governtment the entity to save
     * @return the persisted entity
     */
    Governtment save(Governtment governtment);

    /**
     *  Get all the governtments.
     *
     *  @return the list of entities
     */
    List<Governtment> findAll();

    /**
     *  Get the "id" governtment.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Governtment findOne(Long id);

    /**
     *  Delete the "id" governtment.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
