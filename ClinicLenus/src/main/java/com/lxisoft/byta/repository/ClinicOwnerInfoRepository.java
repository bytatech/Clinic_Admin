package com.lxisoft.byta.repository;

import com.lxisoft.byta.domain.ClinicOwnerInfo;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ClinicOwnerInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClinicOwnerInfoRepository extends JpaRepository<ClinicOwnerInfo,Long> {
    
}
