package com.lxisoft.byta.model;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.lxisoft.byta.serviceImpl.ReceptionistServiceImpl;

import lombok.Data;
/**
 * Token class defines a token
 * @author Karthi
 *@version 1.0.0
 */
@Data


public class Token {
	
	@Autowired
	ReceptionistServiceImpl service;
	
	 public static int tokenNumber = 0;

	private Date date;
	private long time;
	private String username;
/**
 * createToken set date,time and token number in the token
 */
	public Token(String name) {
		
		
		username=name;
		date = new Date();
		date.setTime(date.getTime() + 10);
		time = date.getTime();
		tokenNumber++;
		
		
		
		
	}
	
	
	
	public String toString(){
		return "date="+getDate()+"time="+getTime()+"token="+tokenNumber+"username="+getUsername();
		
	}
	
}
