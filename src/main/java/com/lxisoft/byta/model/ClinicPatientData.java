package com.lxisoft.byta.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
/**
 *  ClinicPatientData class captures patient data.
 *  @author RAFEEK
 * @author Karthi
 * @author Maya
 *@version 1.0.0
 */

@Data

@JsonIgnoreProperties(ignoreUnknown = true)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"name" })})
public class ClinicPatientData {
	private @Id @GeneratedValue Long id;
	
	@DateTimeFormat(pattern="dd-mm-yyyy")
	private Date date;



	@NotNull
	private  String name;

	private String gender;
	

	

	@NotNull
	private  long phoneNo;

	private String familyPhysician;
	
	@JsonIgnore
	private int recordNo;
	
	@JsonIgnore
	private int biometricId;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	@JoinColumn(name = "patientId")
	private ClinicPatientPrivateData privateData;
	
	private @Version Long version;
	
	private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
	

	 
	public ClinicPatientData(){
	
	}
	
	public ClinicPatientData(String name,Long phoneNo){
		this.name=name;
		this.phoneNo=phoneNo;
		
		
	}

}
