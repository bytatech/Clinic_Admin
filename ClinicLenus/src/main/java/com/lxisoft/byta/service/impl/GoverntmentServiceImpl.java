package com.lxisoft.byta.service.impl;

import com.lxisoft.byta.service.GoverntmentService;
import com.lxisoft.byta.domain.Governtment;
import com.lxisoft.byta.repository.GoverntmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Governtment.
 */
@Service
@Transactional
public class GoverntmentServiceImpl implements GoverntmentService{

    private final Logger log = LoggerFactory.getLogger(GoverntmentServiceImpl.class);

    private final GoverntmentRepository governtmentRepository;

    public GoverntmentServiceImpl(GoverntmentRepository governtmentRepository) {
        this.governtmentRepository = governtmentRepository;
    }

    /**
     * Save a governtment.
     *
     * @param governtment the entity to save
     * @return the persisted entity
     */
    @Override
    public Governtment save(Governtment governtment) {
        log.debug("Request to save Governtment : {}", governtment);
        return governtmentRepository.save(governtment);
    }

    /**
     *  Get all the governtments.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Governtment> findAll() {
        log.debug("Request to get all Governtments");
        return governtmentRepository.findAll();
    }

    /**
     *  Get one governtment by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Governtment findOne(Long id) {
        log.debug("Request to get Governtment : {}", id);
        return governtmentRepository.findOne(id);
    }

    /**
     *  Delete the  governtment by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Governtment : {}", id);
        governtmentRepository.delete(id);
    }
}
