package com.lxisoft.byta.repository;

import com.lxisoft.byta.domain.Drugs;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Drugs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DrugsRepository extends JpaRepository<Drugs,Long> {
    
}
