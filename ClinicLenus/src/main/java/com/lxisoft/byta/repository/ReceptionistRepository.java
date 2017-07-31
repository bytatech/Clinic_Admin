package com.lxisoft.byta.repository;

import com.lxisoft.byta.domain.Receptionist;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Receptionist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReceptionistRepository extends JpaRepository<Receptionist,Long> {
    
}
