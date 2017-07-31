package com.lxisoft.byta.service;

import com.lxisoft.byta.domain.MedicineSystem;
import java.util.List;

/**
 * Service Interface for managing MedicineSystem.
 */
public interface MedicineSystemService {

    /**
     * Save a medicineSystem.
     *
     * @param medicineSystem the entity to save
     * @return the persisted entity
     */
    MedicineSystem save(MedicineSystem medicineSystem);

    /**
     *  Get all the medicineSystems.
     *
     *  @return the list of entities
     */
    List<MedicineSystem> findAll();

    /**
     *  Get the "id" medicineSystem.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MedicineSystem findOne(Long id);

    /**
     *  Delete the "id" medicineSystem.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
