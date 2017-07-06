package com.lxisoft.byta.exceptions;

public class UserNameNotRepeatException extends Exception {

	public UserNameNotRepeatException() {
		super();
		System.out.println("Name already exsist.... try another name");
	}

	

}
