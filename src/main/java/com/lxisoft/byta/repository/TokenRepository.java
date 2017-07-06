package com.lxisoft.byta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.lxisoft.byta.model.Token;
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	<S extends Token> S save(S s);
}
