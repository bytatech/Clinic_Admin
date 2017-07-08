/*package com.lxisoft.byta;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import com.lxisoft.byta.model.ClinicPatientData;
import com.lxisoft.byta.repository.ReceptionistRepository;
import com.lxisoft.byta.security.SecurityUtils;



@RunWith(SpringRunner.class)
@SpringBootTest
public class MethodLevelSecurityTest {
	@Autowired
private ReceptionistRepository receptionistRepository;
	@Before
	public void setUp() {
		SecurityContextHolder.clearContext();
	}
	@Test
	public void rejectsMethodInvocationsForNoAuth() {
		try {
			receptionistRepository.findOne(1L);
			fail("Expected a security error");
		} catch (AuthenticationCredentialsNotFoundException e) {
			// expected
		}
		try {
			receptionistRepository.save(new ClinicPatientData());
			fail("Expected a security error");
		} catch (AuthenticationCredentialsNotFoundException e) {
			// expected
		}
		try {
			receptionistRepository.delete(1L);;
			fail("Expected a security error");
		} catch (AuthenticationCredentialsNotFoundException e) {
			// expected
		}
		try {
			receptionistRepository.findOne(1L);
			fail("Expected a security error");
		} catch (AuthenticationCredentialsNotFoundException e) {
			// expected
		}
		
		try {
			receptionistRepository.findByNameContaining("lxi",new PageRequest(2,2));
			fail("Expected a security error");
		} catch (AuthenticationCredentialsNotFoundException e) {
			// expected
		}
		try {
			receptionistRepository. findByPhoneNo(1234L);
			fail("Expected a security error");
		} catch (AuthenticationCredentialsNotFoundException e) {
			// expected
		}
		
	}



}
*/