package com.lxisoft.byta.service.impl;

import com.lxisoft.byta.service.ClinicService;
import com.lxisoft.byta.domain.Clinic;
import com.lxisoft.byta.repository.ClinicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Clinic.
 */
@Service
@Transactional
public class ClinicServiceImpl implements ClinicService{

    private final Logger log = LoggerFactory.getLogger(ClinicServiceImpl.class);

    private final ClinicRepository clinicRepository;

    public ClinicServiceImpl(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    /**
     * Save a clinic.
     *
     * @param clinic the entity to save
     * @return the persisted entity
     */
    @Override
    public Clinic save(Clinic clinic) {
        log.debug("Request to save Clinic : {}", clinic);
        return clinicRepository.save(clinic);
    }

    /**
     *  Get all the clinics.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Clinic> findAll() {
        log.debug("Request to get all Clinics");
        return clinicRepository.findAll();
    }

    /**
     *  Get one clinic by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Clinic findOne(Long id) {
        log.debug("Request to get Clinic : {}", id);
        return clinicRepository.findOne(id);
    }

    /**
     *  Delete the  clinic by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Clinic : {}", id);
        clinicRepository.delete(id);
    }
}
