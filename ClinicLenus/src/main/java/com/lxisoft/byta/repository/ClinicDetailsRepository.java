package com.lxisoft.byta.repository;

import com.lxisoft.byta.domain.ClinicDetails;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ClinicDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClinicDetailsRepository extends JpaRepository<ClinicDetails,Long> {
    
}
