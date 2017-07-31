package com.lxisoft.byta.service.impl;

import com.lxisoft.byta.service.ClinicOwnerInfoService;
import com.lxisoft.byta.domain.ClinicOwnerInfo;
import com.lxisoft.byta.repository.ClinicOwnerInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing ClinicOwnerInfo.
 */
@Service
@Transactional
public class ClinicOwnerInfoServiceImpl implements ClinicOwnerInfoService{

    private final Logger log = LoggerFactory.getLogger(ClinicOwnerInfoServiceImpl.class);

    private final ClinicOwnerInfoRepository clinicOwnerInfoRepository;

    public ClinicOwnerInfoServiceImpl(ClinicOwnerInfoRepository clinicOwnerInfoRepository) {
        this.clinicOwnerInfoRepository = clinicOwnerInfoRepository;
    }

    /**
     * Save a clinicOwnerInfo.
     *
     * @param clinicOwnerInfo the entity to save
     * @return the persisted entity
     */
    @Override
    public ClinicOwnerInfo save(ClinicOwnerInfo clinicOwnerInfo) {
        log.debug("Request to save ClinicOwnerInfo : {}", clinicOwnerInfo);
        return clinicOwnerInfoRepository.save(clinicOwnerInfo);
    }

    /**
     *  Get all the clinicOwnerInfos.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ClinicOwnerInfo> findAll() {
        log.debug("Request to get all ClinicOwnerInfos");
        return clinicOwnerInfoRepository.findAll();
    }

    /**
     *  Get one clinicOwnerInfo by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ClinicOwnerInfo findOne(Long id) {
        log.debug("Request to get ClinicOwnerInfo : {}", id);
        return clinicOwnerInfoRepository.findOne(id);
    }

    /**
     *  Delete the  clinicOwnerInfo by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClinicOwnerInfo : {}", id);
        clinicOwnerInfoRepository.delete(id);
    }
}
