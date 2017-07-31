package com.lxisoft.byta.service.impl;

import com.lxisoft.byta.service.NonGoverntmentService;
import com.lxisoft.byta.domain.NonGoverntment;
import com.lxisoft.byta.repository.NonGoverntmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing NonGoverntment.
 */
@Service
@Transactional
public class NonGoverntmentServiceImpl implements NonGoverntmentService{

    private final Logger log = LoggerFactory.getLogger(NonGoverntmentServiceImpl.class);

    private final NonGoverntmentRepository nonGoverntmentRepository;

    public NonGoverntmentServiceImpl(NonGoverntmentRepository nonGoverntmentRepository) {
        this.nonGoverntmentRepository = nonGoverntmentRepository;
    }

    /**
     * Save a nonGoverntment.
     *
     * @param nonGoverntment the entity to save
     * @return the persisted entity
     */
    @Override
    public NonGoverntment save(NonGoverntment nonGoverntment) {
        log.debug("Request to save NonGoverntment : {}", nonGoverntment);
        return nonGoverntmentRepository.save(nonGoverntment);
    }

    /**
     *  Get all the nonGoverntments.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<NonGoverntment> findAll() {
        log.debug("Request to get all NonGoverntments");
        return nonGoverntmentRepository.findAll();
    }

    /**
     *  Get one nonGoverntment by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public NonGoverntment findOne(Long id) {
        log.debug("Request to get NonGoverntment : {}", id);
        return nonGoverntmentRepository.findOne(id);
    }

    /**
     *  Delete the  nonGoverntment by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NonGoverntment : {}", id);
        nonGoverntmentRepository.delete(id);
    }
}
