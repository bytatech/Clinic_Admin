package com.lxisoft.byta.repository;

import com.lxisoft.byta.domain.Clinic;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Clinic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClinicRepository extends JpaRepository<Clinic,Long> {
    
}
