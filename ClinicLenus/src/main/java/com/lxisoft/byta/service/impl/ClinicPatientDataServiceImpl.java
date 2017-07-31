package com.lxisoft.byta.service.impl;

import com.lxisoft.byta.service.ClinicPatientDataService;
import com.lxisoft.byta.domain.ClinicPatientData;
import com.lxisoft.byta.repository.ClinicPatientDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing ClinicPatientData.
 */
@Service
@Transactional
public class ClinicPatientDataServiceImpl implements ClinicPatientDataService{

    private final Logger log = LoggerFactory.getLogger(ClinicPatientDataServiceImpl.class);

    private final ClinicPatientDataRepository clinicPatientDataRepository;

    public ClinicPatientDataServiceImpl(ClinicPatientDataRepository clinicPatientDataRepository) {
        this.clinicPatientDataRepository = clinicPatientDataRepository;
    }

    /**
     * Save a clinicPatientData.
     *
     * @param clinicPatientData the entity to save
     * @return the persisted entity
     */
    @Override
    public ClinicPatientData save(ClinicPatientData clinicPatientData) {
        log.debug("Request to save ClinicPatientData : {}", clinicPatientData);
        return clinicPatientDataRepository.save(clinicPatientData);
    }

    /**
     *  Get all the clinicPatientData.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ClinicPatientData> findAll() {
        log.debug("Request to get all ClinicPatientData");
        return clinicPatientDataRepository.findAll();
    }

    /**
     *  Get one clinicPatientData by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ClinicPatientData findOne(Long id) {
        log.debug("Request to get ClinicPatientData : {}", id);
        return clinicPatientDataRepository.findOne(id);
    }

    /**
     *  Delete the  clinicPatientData by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClinicPatientData : {}", id);
        clinicPatientDataRepository.delete(id);
    }
}
