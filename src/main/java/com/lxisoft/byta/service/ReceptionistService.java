package com.lxisoft.byta.service;

import org.springframework.data.domain.Page;

import com.lxisoft.byta.model.ClinicPatientData;
import com.lxisoft.byta.model.Token;



public interface ReceptionistService {

	public void save(ClinicPatientData data);

	public ClinicPatientData findOne(Long id);
	
	public Token findOne(int id);
	
	public ClinicPatientData findByPhoneNo(Long phoneNo);

	public Page<ClinicPatientData> getPatientData(String name, int pageNumber);

	
	
	
}
