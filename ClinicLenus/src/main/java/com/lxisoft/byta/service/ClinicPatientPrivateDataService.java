package com.lxisoft.byta.service;

import com.lxisoft.byta.domain.ClinicPatientPrivateData;
import java.util.List;

/**
 * Service Interface for managing ClinicPatientPrivateData.
 */
public interface ClinicPatientPrivateDataService {

    /**
     * Save a clinicPatientPrivateData.
     *
     * @param clinicPatientPrivateData the entity to save
     * @return the persisted entity
     */
    ClinicPatientPrivateData save(ClinicPatientPrivateData clinicPatientPrivateData);

    /**
     *  Get all the clinicPatientPrivateData.
     *
     *  @return the list of entities
     */
    List<ClinicPatientPrivateData> findAll();

    /**
     *  Get the "id" clinicPatientPrivateData.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ClinicPatientPrivateData findOne(Long id);

    /**
     *  Delete the "id" clinicPatientPrivateData.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
