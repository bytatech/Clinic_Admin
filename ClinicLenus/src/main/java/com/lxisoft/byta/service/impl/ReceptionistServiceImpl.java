package com.lxisoft.byta.service.impl;

import com.lxisoft.byta.service.ReceptionistService;
import com.lxisoft.byta.domain.Receptionist;
import com.lxisoft.byta.repository.ReceptionistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Receptionist.
 */
@Service
@Transactional
public class ReceptionistServiceImpl implements ReceptionistService{

    private final Logger log = LoggerFactory.getLogger(ReceptionistServiceImpl.class);

    private final ReceptionistRepository receptionistRepository;

    public ReceptionistServiceImpl(ReceptionistRepository receptionistRepository) {
        this.receptionistRepository = receptionistRepository;
    }

    /**
     * Save a receptionist.
     *
     * @param receptionist the entity to save
     * @return the persisted entity
     */
    @Override
    public Receptionist save(Receptionist receptionist) {
        log.debug("Request to save Receptionist : {}", receptionist);
        return receptionistRepository.save(receptionist);
    }

    /**
     *  Get all the receptionists.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Receptionist> findAll() {
        log.debug("Request to get all Receptionists");
        return receptionistRepository.findAll();
    }

    /**
     *  Get one receptionist by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Receptionist findOne(Long id) {
        log.debug("Request to get Receptionist : {}", id);
        return receptionistRepository.findOne(id);
    }

    /**
     *  Delete the  receptionist by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Receptionist : {}", id);
        receptionistRepository.delete(id);
    }
}
