package com.lxisoft.byta.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
/**
 *  Drug details related to prescription.
 * @author Maya
 *@version 1.0.0
 */

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EntityListeners(AuditingEntityListener.class)
public class Drug {

	@Id @GeneratedValue
	private long patientDrugId;
	
	private String drugName;
	private int dose;
	private String dosageInstruction;
}
