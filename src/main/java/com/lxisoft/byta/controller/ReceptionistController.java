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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lxisoft.byta.exceptions.UserNameNotRepeatException;
import com.lxisoft.byta.model.ClinicPatientData;
import com.lxisoft.byta.model.ClinicPatientPrivateData;
import com.lxisoft.byta.model.Patient;
import com.lxisoft.byta.model.Prescription;
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
	
	

	@RequestMapping(value = "/qrReader", method = RequestMethod.POST, consumes = "multipart/form-data")
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

		ClinicPatientPrivateData privateData = new ClinicPatientPrivateData();

		ClinicPatientData data = new ClinicPatientData();

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

	// patient data CRUD request mapping
	
	@RequestMapping("/1")
	public String print(){
		return "hii";
	}

	@RequestMapping(value="/register", method = RequestMethod.POST)

	public void savePatient(ClinicPatientData data)throws UserNameNotRepeatException {
		
	
		service.save(data);
		
	}

	
	@RequestMapping(value="/read/{id}", method = RequestMethod.GET)
	public ClinicPatientData readPatient(@PathVariable Long id) {
		

    	
		
		return service.findOne(id);
	}

	

	@RequestMapping(value="/searchbyname/{name}/{pageNumber}", method = RequestMethod.GET)
	public Page<ClinicPatientData> serchByName(@PathVariable String name,@PathVariable  int pageNumber) {
		

	
	
	return service.getPatientData(name, pageNumber);
	}

	@RequestMapping(value="/searchbyphone/{phoneNo}", method = RequestMethod.POST)

	public ClinicPatientData findByPhoneNo(@PathVariable Long phoneNo) {
		

    	
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
	
	

	 @RequestMapping(value="/generateToken/{name}", method = RequestMethod.GET)
     public  Token generateToken(@PathVariable String name ) {
		
		 number++;
		 
		 Token token = new Token(name,number);
		 
		 /*ObjectMapper mapper=new ObjectMapper();
		 Token t=mapper.readValue(new URL("http://localhost:8080/setuser?username="+username+"&"+"adress="+adress), User.class);*/
		 	saveToken(token);
		 	
		 	return token; 
	 
	 }
	 
	 
	 @RequestMapping("/saveToken")
    public  void saveToken(Token token ) {
		 
		 
    	
    	
		 service.save(token);
		 
		
	 }
    
    
    
	 
	 @RequestMapping(value="/doctor/read/{id}", method = RequestMethod.GET)
	 public ClinicPatientData getPatientData(@PathVariable Long id){
		 
	    	
	    	
		 return service.findOne(id); 
		 
	 }
	 
	 
	 @RequestMapping(value="/doctor/getToken/{id}", method = RequestMethod.GET)
	 public Token getTokenNumber(@PathVariable int id){
		 
		 
	    	
		 return service.findOne(id); 
		 
	 }
	 
	 
	 @RequestMapping(value="/doctor/savePrescription/{id}", method = RequestMethod.POST)
	 public void savePrescription(Long id){
		 
	
		 
		 ClinicPatientData data = service.findOne(id);
		 List<Prescription> prescriptions = null;

			
			data.setPrescriptionList(prescriptions);

			
			 service.save(data);
		 
		 
	 }
	 
	 
	 
	 @RequestMapping(value = "/readPrescription/{id}", method = RequestMethod.GET)
		public List<Prescription> getPatienPrescriptiont(@PathVariable Long id) {
		 
			
		
			return service.findOne(id).getPrescriptionList();
		}
	 
	 
	
	
}
