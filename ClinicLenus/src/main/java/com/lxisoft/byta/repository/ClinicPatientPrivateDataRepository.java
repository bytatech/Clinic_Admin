package com.lxisoft.byta.repository;

import com.lxisoft.byta.domain.ClinicPatientPrivateData;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ClinicPatientPrivateData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClinicPatientPrivateDataRepository extends JpaRepository<ClinicPatientPrivateData,Long> {
    
}
