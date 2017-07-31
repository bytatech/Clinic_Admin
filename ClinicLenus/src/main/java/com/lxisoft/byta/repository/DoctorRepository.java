package com.lxisoft.byta.repository;

import com.lxisoft.byta.domain.Doctor;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Doctor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    
}
