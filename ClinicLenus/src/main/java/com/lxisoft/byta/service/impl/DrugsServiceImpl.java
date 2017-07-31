package com.lxisoft.byta.service.impl;

import com.lxisoft.byta.service.DrugsService;
import com.lxisoft.byta.domain.Drugs;
import com.lxisoft.byta.repository.DrugsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Drugs.
 */
@Service
@Transactional
public class DrugsServiceImpl implements DrugsService{

    private final Logger log = LoggerFactory.getLogger(DrugsServiceImpl.class);

    private final DrugsRepository drugsRepository;

    public DrugsServiceImpl(DrugsRepository drugsRepository) {
        this.drugsRepository = drugsRepository;
    }

    /**
     * Save a drugs.
     *
     * @param drugs the entity to save
     * @return the persisted entity
     */
    @Override
    public Drugs save(Drugs drugs) {
        log.debug("Request to save Drugs : {}", drugs);
        return drugsRepository.save(drugs);
    }

    /**
     *  Get all the drugs.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Drugs> findAll() {
        log.debug("Request to get all Drugs");
        return drugsRepository.findAll();
    }

    /**
     *  Get one drugs by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Drugs findOne(Long id) {
        log.debug("Request to get Drugs : {}", id);
        return drugsRepository.findOne(id);
    }

    /**
     *  Delete the  drugs by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Drugs : {}", id);
        drugsRepository.delete(id);
    }
}
