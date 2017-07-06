package com.lxisoft.byta.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
/**
 * ClinicPatientPrivateData captures  patient private details 
 *  @author RAFEEK
 * @author Karthi
 * @author Maya
 * @version 1.0.0
 *
 */
@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown =true)
public class ClinicPatientPrivateData {
	@Id @GeneratedValue
	private long patientId;
	private String address;
	private int age;
	private String city;
	private int zip;
	private String email;
	private String socialMediaId;
	private String profession;
	private String country;
	
	
}
