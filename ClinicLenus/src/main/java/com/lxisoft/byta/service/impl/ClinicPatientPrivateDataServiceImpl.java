package com.lxisoft.byta.service.impl;

import com.lxisoft.byta.service.ClinicPatientPrivateDataService;
import com.lxisoft.byta.domain.ClinicPatientPrivateData;
import com.lxisoft.byta.repository.ClinicPatientPrivateDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing ClinicPatientPrivateData.
 */
@Service
@Transactional
public class ClinicPatientPrivateDataServiceImpl implements ClinicPatientPrivateDataService{

    private final Logger log = LoggerFactory.getLogger(ClinicPatientPrivateDataServiceImpl.class);

    private final ClinicPatientPrivateDataRepository clinicPatientPrivateDataRepository;

    public ClinicPatientPrivateDataServiceImpl(ClinicPatientPrivateDataRepository clinicPatientPrivateDataRepository) {
        this.clinicPatientPrivateDataRepository = clinicPatientPrivateDataRepository;
    }

    /**
     * Save a clinicPatientPrivateData.
     *
     * @param clinicPatientPrivateData the entity to save
     * @return the persisted entity
     */
    @Override
    public ClinicPatientPrivateData save(ClinicPatientPrivateData clinicPatientPrivateData) {
        log.debug("Request to save ClinicPatientPrivateData : {}", clinicPatientPrivateData);
        return clinicPatientPrivateDataRepository.save(clinicPatientPrivateData);
    }

    /**
     *  Get all the clinicPatientPrivateData.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ClinicPatientPrivateData> findAll() {
        log.debug("Request to get all ClinicPatientPrivateData");
        return clinicPatientPrivateDataRepository.findAll();
    }

    /**
     *  Get one clinicPatientPrivateData by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ClinicPatientPrivateData findOne(Long id) {
        log.debug("Request to get ClinicPatientPrivateData : {}", id);
        return clinicPatientPrivateDataRepository.findOne(id);
    }

    /**
     *  Delete the  clinicPatientPrivateData by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClinicPatientPrivateData : {}", id);
        clinicPatientPrivateDataRepository.delete(id);
    }
}
