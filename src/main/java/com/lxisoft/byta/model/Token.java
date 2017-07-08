package com.lxisoft.byta.model;

import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lxisoft.byta.serviceImpl.ReceptionistServiceImpl;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Token class defines a token
 * @author Karthi
 *@version 1.0.0
 */
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@NoArgsConstructor
//@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"tokenNumber" })})

public class Token {
	
	@Id @GeneratedValue
    private int id;

	 
	@DateTimeFormat(pattern="dd-mm-yyyy")
	private Date date;
	private long time;
	private int tokenNumber;
	private String username;
/**
 * createToken set date,time and token number in the token
 */
	public Token(String name,int number) {
		
		
		username=name;
		date = new Date();
		date.setTime(date.getTime() + 10);
		time = date.getTime();
		tokenNumber=number;
		
		
		
		
	}
	
	
	
	/*public String toString(){
		return "date="+getDate()+"time="+getTime()+"token="+tokenNumber+"username="+getUsername();
		
	}
	*/
}
