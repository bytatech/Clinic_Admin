package com.lxisoft.byta.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.lxisoft.byta.model.ClinicPatientData;
import com.lxisoft.byta.model.Token;
import com.lxisoft.byta.repository.ReceptionistRepository;
import com.lxisoft.byta.repository.TokenRepository;
import com.lxisoft.byta.service.ReceptionistService;


/**
 * The ReceptionistServiceImpl class defines services
 * 
 * @author RAFEEK
 * @author karthi
 * @author Maya
 * @version 1.0.0
 */


@Service
public class ReceptionistServiceImpl implements ReceptionistService{

	
	@Autowired
	private ReceptionistRepository patientRepo;
	
	@Autowired
	private TokenRepository tokenRepo;
	
	
	
	
	public void save(ClinicPatientData data) {
		patientRepo.save(data);
	}


	public ClinicPatientData findOne(Long id) {

		return patientRepo.findOne(id);
	}

	
	public List<ClinicPatientData> findAll() {
		return patientRepo.findAll();
	}


	
	public ClinicPatientData findByPhoneNo(Long phoneNo) {
		
		return patientRepo.findByPhoneNo(phoneNo);
	}


	
	public Page<ClinicPatientData> getPatientData(String name, int pageNumber) {
		return patientRepo.findByNameContaining(name, new PageRequest(pageNumber - 1, 2));
	}


	public String findOne(String name) {
	
		return patientRepo.findByName(name);
	}


	public void save(Token token) {
		tokenRepo.save(token);
		
	}


	public Token findOne(int id) {
		
		return tokenRepo.findOne(id);
	}


	

	

	
	

}
