package com.lxisoft.byta.service;

import com.lxisoft.byta.domain.ClinicOwnerInfo;
import java.util.List;

/**
 * Service Interface for managing ClinicOwnerInfo.
 */
public interface ClinicOwnerInfoService {

    /**
     * Save a clinicOwnerInfo.
     *
     * @param clinicOwnerInfo the entity to save
     * @return the persisted entity
     */
    ClinicOwnerInfo save(ClinicOwnerInfo clinicOwnerInfo);

    /**
     *  Get all the clinicOwnerInfos.
     *
     *  @return the list of entities
     */
    List<ClinicOwnerInfo> findAll();

    /**
     *  Get the "id" clinicOwnerInfo.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ClinicOwnerInfo findOne(Long id);

    /**
     *  Delete the "id" clinicOwnerInfo.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
