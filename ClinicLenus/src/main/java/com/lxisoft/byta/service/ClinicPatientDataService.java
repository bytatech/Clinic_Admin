package com.lxisoft.byta.service;

import com.lxisoft.byta.domain.ClinicPatientData;
import java.util.List;

/**
 * Service Interface for managing ClinicPatientData.
 */
public interface ClinicPatientDataService {

    /**
     * Save a clinicPatientData.
     *
     * @param clinicPatientData the entity to save
     * @return the persisted entity
     */
    ClinicPatientData save(ClinicPatientData clinicPatientData);

    /**
     *  Get all the clinicPatientData.
     *
     *  @return the list of entities
     */
    List<ClinicPatientData> findAll();

    /**
     *  Get the "id" clinicPatientData.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ClinicPatientData findOne(Long id);

    /**
     *  Delete the "id" clinicPatientData.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
