package com.lxisoft.byta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.lxisoft.byta.model.ClinicPatientData;
import com.lxisoft.byta.model.Token;
@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

	@PreAuthorize("hasRole('ROLE_RECEPTIONIST')")
	<S extends Token> S save(S s);
	
	
	/*@PreAuthorize("hasRole('ROLE_DOCTOR')")
	public Token findOne(Long patientId);*/

	@PreAuthorize("hasRole('ROLE_RECEPTIONIST')")
	public	Token findOne(int id);
	
}
