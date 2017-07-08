package com.lxisoft.byta.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * XML data of patient from QR code is take here
 * 
 * @author Maya
 * @version 1.0.0
 */

@XmlRootElement
public class Patient {
	
	private int id;
	private String name;
	private int age;
	private long ph;
	private String gender;
	private String house;
	private String street;
	private int zip;
	
	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Patient() {
	}

	

	public Patient(int id, String name, int age, long ph, String gender, String house, String street, int zip) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.ph = ph;
		this.gender = gender;
		this.house = house;
		this.street = street;
		this.zip = zip;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@XmlElement
	public long getPh() {
		return ph;
	}

	public void setPh(long ph) {
		this.ph = ph;
	}

	@XmlElement
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	@XmlElement
	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}
	@XmlElement
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	@XmlElement
	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

}
