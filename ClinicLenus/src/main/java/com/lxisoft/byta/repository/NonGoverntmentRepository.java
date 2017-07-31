package com.lxisoft.byta.repository;

import com.lxisoft.byta.domain.NonGoverntment;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the NonGoverntment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NonGoverntmentRepository extends JpaRepository<NonGoverntment,Long> {
    
}
