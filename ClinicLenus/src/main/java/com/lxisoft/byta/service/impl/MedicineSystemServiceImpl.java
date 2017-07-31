package com.lxisoft.byta.service.impl;

import com.lxisoft.byta.service.MedicineSystemService;
import com.lxisoft.byta.domain.MedicineSystem;
import com.lxisoft.byta.repository.MedicineSystemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing MedicineSystem.
 */
@Service
@Transactional
public class MedicineSystemServiceImpl implements MedicineSystemService{

    private final Logger log = LoggerFactory.getLogger(MedicineSystemServiceImpl.class);

    private final MedicineSystemRepository medicineSystemRepository;

    public MedicineSystemServiceImpl(MedicineSystemRepository medicineSystemRepository) {
        this.medicineSystemRepository = medicineSystemRepository;
    }

    /**
     * Save a medicineSystem.
     *
     * @param medicineSystem the entity to save
     * @return the persisted entity
     */
    @Override
    public MedicineSystem save(MedicineSystem medicineSystem) {
        log.debug("Request to save MedicineSystem : {}", medicineSystem);
        return medicineSystemRepository.save(medicineSystem);
    }

    /**
     *  Get all the medicineSystems.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MedicineSystem> findAll() {
        log.debug("Request to get all MedicineSystems");
        return medicineSystemRepository.findAll();
    }

    /**
     *  Get one medicineSystem by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MedicineSystem findOne(Long id) {
        log.debug("Request to get MedicineSystem : {}", id);
        return medicineSystemRepository.findOne(id);
    }

    /**
     *  Delete the  medicineSystem by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MedicineSystem : {}", id);
        medicineSystemRepository.delete(id);
    }
}
