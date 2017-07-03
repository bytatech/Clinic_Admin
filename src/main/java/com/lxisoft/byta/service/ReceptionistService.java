package com.lxisoft.byta.service;

import org.springframework.data.domain.Page;

import com.lxisoft.byta.model.ClinicPatientData;



public interface ReceptionistService {

	public void save(ClinicPatientData data);

	public ClinicPatientData findOne(Long id);

	public ClinicPatientData findByPhoneNo(Long phoneNo);

	public Page<ClinicPatientData> getPatientData(String name, int pageNumber);

	
	
	
}
