package com.lxisoft.byta.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.lxisoft.byta.model.ClinicPatientData;
import com.lxisoft.byta.model.Prescription;
import com.lxisoft.byta.model.Token;

/**
 * this is an interface to create a repository which extends {@link JpaRepository}
 * 
 * @author RAFEEK
 * @author Karthi
 * @author Maya
 * @version 1.0.0
 */

@Repository
public interface ReceptionistRepository extends JpaRepository<ClinicPatientData, Long> {

	@PreAuthorize("hasRole('ROLE_RECEPTIONIST')")
	<S extends ClinicPatientData> S save(S s);

	@PreAuthorize("hasRole('ROLE_RECEPTIONIST')")
	public ClinicPatientData findOne(Long patientId);

	@PreAuthorize("hasRole('ROLE_RECEPTIONIST')")
	public ClinicPatientData findByPhoneNo(Long phoneNo);

	

	@PreAuthorize("hasRole('ROLE_RECEPTIONIST')")
	public List<ClinicPatientData> findAll();

	@PreAuthorize("hasRole('ROLE_RECEPTIONIST')")
	public Page<ClinicPatientData> findByNameContaining(String name, Pageable pageRequest);
	
	@PreAuthorize("hasRole('ROLE_RECEPTIONIST')")
	public String findByName(String name);

	



}
