package com.lxisoft.byta.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * Prescription deatils of patient are stored here.
 * @author Maya
 *@version 1.0.0
 */


@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Prescription {
	
	
	@Id @GeneratedValue
	private long patientPrescriptionId;
	
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval =true)
	private List<Drug> drugList;
	
	
	private String doctorName;
	
}
