package com.lxisoft.byta.repository;

import com.lxisoft.byta.domain.Governtment;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Governtment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GoverntmentRepository extends JpaRepository<Governtment,Long> {
    
}
