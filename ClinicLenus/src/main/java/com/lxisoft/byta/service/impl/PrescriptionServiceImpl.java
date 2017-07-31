package com.lxisoft.byta.service.impl;

import com.lxisoft.byta.service.PrescriptionService;
import com.lxisoft.byta.domain.Prescription;
import com.lxisoft.byta.repository.PrescriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Prescription.
 */
@Service
@Transactional
public class PrescriptionServiceImpl implements PrescriptionService{

    private final Logger log = LoggerFactory.getLogger(PrescriptionServiceImpl.class);

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    /**
     * Save a prescription.
     *
     * @param prescription the entity to save
     * @return the persisted entity
     */
    @Override
    public Prescription save(Prescription prescription) {
        log.debug("Request to save Prescription : {}", prescription);
        return prescriptionRepository.save(prescription);
    }

    /**
     *  Get all the prescriptions.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Prescription> findAll() {
        log.debug("Request to get all Prescriptions");
        return prescriptionRepository.findAllWithEagerRelationships();
    }

    /**
     *  Get one prescription by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Prescription findOne(Long id) {
        log.debug("Request to get Prescription : {}", id);
        return prescriptionRepository.findOneWithEagerRelationships(id);
    }

    /**
     *  Delete the  prescription by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Prescription : {}", id);
        prescriptionRepository.delete(id);
    }
}
