package com.lxisoft.byta.repository;

import com.lxisoft.byta.domain.ClinicPatientData;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ClinicPatientData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClinicPatientDataRepository extends JpaRepository<ClinicPatientData,Long> {
    
}
