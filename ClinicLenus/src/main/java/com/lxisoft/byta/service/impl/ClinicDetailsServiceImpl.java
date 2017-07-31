package com.lxisoft.byta.service.impl;

import com.lxisoft.byta.service.ClinicDetailsService;
import com.lxisoft.byta.domain.ClinicDetails;
import com.lxisoft.byta.repository.ClinicDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing ClinicDetails.
 */
@Service
@Transactional
public class ClinicDetailsServiceImpl implements ClinicDetailsService{

    private final Logger log = LoggerFactory.getLogger(ClinicDetailsServiceImpl.class);

    private final ClinicDetailsRepository clinicDetailsRepository;

    public ClinicDetailsServiceImpl(ClinicDetailsRepository clinicDetailsRepository) {
        this.clinicDetailsRepository = clinicDetailsRepository;
    }

    /**
     * Save a clinicDetails.
     *
     * @param clinicDetails the entity to save
     * @return the persisted entity
     */
    @Override
    public ClinicDetails save(ClinicDetails clinicDetails) {
        log.debug("Request to save ClinicDetails : {}", clinicDetails);
        return clinicDetailsRepository.save(clinicDetails);
    }

    /**
     *  Get all the clinicDetails.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ClinicDetails> findAll() {
        log.debug("Request to get all ClinicDetails");
        return clinicDetailsRepository.findAll();
    }

    /**
     *  Get one clinicDetails by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ClinicDetails findOne(Long id) {
        log.debug("Request to get ClinicDetails : {}", id);
        return clinicDetailsRepository.findOne(id);
    }

    /**
     *  Delete the  clinicDetails by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClinicDetails : {}", id);
        clinicDetailsRepository.delete(id);
    }
}
