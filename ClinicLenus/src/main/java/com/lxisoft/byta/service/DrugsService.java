package com.lxisoft.byta.service;

import com.lxisoft.byta.domain.Drugs;
import java.util.List;

/**
 * Service Interface for managing Drugs.
 */
public interface DrugsService {

    /**
     * Save a drugs.
     *
     * @param drugs the entity to save
     * @return the persisted entity
     */
    Drugs save(Drugs drugs);

    /**
     *  Get all the drugs.
     *
     *  @return the list of entities
     */
    List<Drugs> findAll();

    /**
     *  Get the "id" drugs.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Drugs findOne(Long id);

    /**
     *  Delete the "id" drugs.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
