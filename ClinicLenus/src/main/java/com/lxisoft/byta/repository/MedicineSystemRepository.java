package com.lxisoft.byta.repository;

import com.lxisoft.byta.domain.MedicineSystem;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MedicineSystem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MedicineSystemRepository extends JpaRepository<MedicineSystem,Long> {
    
}
