package com.lxisoft.byta.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.lxisoft.byta.exceptions.UserNameNotRepeatException;
import com.lxisoft.byta.model.ClinicPatientData;
import com.lxisoft.byta.model.Token;
import com.lxisoft.byta.serviceImpl.ReceptionistServiceImpl;



/**
 * Controller which populates
 * the patient current data to database of clinic and the CRUD operations.
 * 
 *  @author RAFEEK
 * @author Karthi
 * @author Maya
 * @version 1.0.0
 * 
 */

@RestController
@RequestMapping("/receptionist")
public class ReceptionistController {
	
	 int number=0;

	@Autowired
	ReceptionistServiceImpl service;
	
	Logger logger = Logger.getLogger(ReceptionistController.class);

	/*@RequestMapping(value = "/qrReader", method = RequestMethod.POST, consumes = "multipart/form-data")
	public Patient getUser(MultipartFile file) throws JAXBException, IOException, UserNameNotRepeatException {

		// conveting xml multipart to io file
		File convFile = new File(file.getOriginalFilename());

		convFile.createNewFile();

		FileOutputStream fos = new FileOutputStream(convFile);

		fos.write(file.getBytes());

		// Converting xml file to Object
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		Patient patient = (Patient) jaxbUnmarshaller.unmarshal(convFile);

		PatientPrivateData privateData = new PatientPrivateData();

		PatientData data = new PatientData();

		data.setName(patient.getName());

		data.setPhoneNo(patient.getPh());

		data.setGender(patient.getGender());

		privateData.setAddress(patient.getHouse() + " ho, " + patient.getStreet());

		privateData.setZip(patient.getZip());
		
		data.setPrivateData(privateData);
		service.save(data);

		fos.close();
		return patient;

	}

*/	// patient data CRUD request mapping
	
	@RequestMapping("/1")
	public String print(){
		return "hii";
	}

	@RequestMapping(value="/register", method = RequestMethod.POST)

	public void savePatient(ClinicPatientData data)throws UserNameNotRepeatException {
		logger.debug("------into savePatient()-----");
	
		service.save(data);
		logger.debug("------saved-----");
	}

	@RequestMapping(value="/update", method = RequestMethod.PUT)
	
		
	public ClinicPatientData updatePatient(ClinicPatientData data) {


    	logger.debug("------get into updatePatient() -------");
    	
		
		ClinicPatientData datas = service.findOne(data.getId());

		datas.setId(data.getId());
		datas.setName(data.getName());
		datas.setPhoneNo(data.getPhoneNo());
		datas.setFamilyPhysician(data.getFamilyPhysician());
		datas.setBiometricId(data.getBiometricId());
		datas.setGender(data.getGender());
		datas.setRecordNo(data.getRecordNo());
		datas.setDate(data.getDate());
		
		
		
	
	datas.getPrivateData().setAddress(data.getPrivateData().getAddress());
	datas.getPrivateData().setAge(data.getPrivateData().getAge());
	datas.getPrivateData().setCity(data.getPrivateData().getCity());
	datas.getPrivateData().setCountry(data.getPrivateData().getCountry());
	datas.getPrivateData().setEmail(data.getPrivateData().getEmail());
	datas.getPrivateData().setProfession(data.getPrivateData().getProfession());
	datas.getPrivateData().setSocialMediaId(data.getPrivateData().getSocialMediaId());
	datas.getPrivateData().setZip(data.getPrivateData().getZip());
		
		//datas.setPrivateData(prvteData);
		//service.savePrivateData(prvteData);
		service.save(datas);
		logger.debug("------saved-----");
		
		logger.debug("return datas");
		return datas;
	}

	@RequestMapping(value="/read/{id}", method = RequestMethod.GET)
	public ClinicPatientData readPatient(@PathVariable Long id) {
		

    	logger.debug("------get into readPatient() -------");
    	logger.debug("return findOne()");
		
		return service.findOne(id);
	}

	/*@RequestMapping(value="/delete{id}", method = RequestMethod.DELETE)
	public void deletePatient(@PathVariable Long id) {
		service.delete(id);

	}

*/	@RequestMapping(value="/searchbyname/{name}/{pageNumber}", method = RequestMethod.GET)
	public Page<ClinicPatientData> serchByName(@PathVariable String name,@PathVariable  int pageNumber) {
		

	logger.debug("------get into searchByName() -------");
	logger.debug("return getPatientData()");
	
	return service.getPatientData(name, pageNumber);
	}

	@RequestMapping(value="/searchbyphone/{phoneNo}", method = RequestMethod.POST)

	public ClinicPatientData findByPhoneNo(@PathVariable Long phoneNo) {
		

    	logger.debug("------get into findByPhoneNo() -------");
    	logger.debug("return findByPhoneNo()");
		
		return service.findByPhoneNo(phoneNo);

	}
	
	
/*	 //R & D code of generating token randomly
     @RequestMapping("/generateToken/{username}")
     public  String generateToken(@PathVariable String username ) {
    	 
    	 SecureRandom random = new SecureRandom();
             long longToken = Math.abs( random.nextLong() );
             String randomtoken = Long.toString( longToken, 16 );
             return ( username + ":" + randomtoken );
     }*/
	
	

	 @RequestMapping("/generateToken/{name}")
     public  Token generateToken(@PathVariable String name ) {
		 
		 
		 
		 
		 number++;
		 logger.debug("------entered into -------");
		 logger.debug("-----number =  -------"+number);
		 Token token = new Token(name,number);
		 
		 logger.debug("------generated Token-------");
		 	saveToken(token);
		 	
		return token; 
	 
	 }
	 
	 

    public  void saveToken(Token token ) {
		 
		 
    	logger.debug("------reached saveToken()-------");
		 service.save(token);
		 logger.debug("------savedToken-------");
	 }
    
    
    
	 
	 @RequestMapping(value="/doctor/read/{id}", method = RequestMethod.GET)
	 public ClinicPatientData getPatientData(@PathVariable Long id){
		 
	    	logger.debug("------get into grtPatientData() -------");
	    	logger.debug("return findOne()");
		 return service.findOne(id); 
		 
	 }
	 
	 
	 @RequestMapping(value="/doctor/getToken/{id}", method = RequestMethod.GET)
	 public Token getTokenNumber(@PathVariable int id){
		 logger.debug("------get into getTokenNumber() -------");
	    	logger.debug("return findOne()");
		 return service.findOne(id); 
		 
	 }
	 
	 
	 
	
	
}
