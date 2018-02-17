package com.neotechlabs.webapp.service;

public class UserValidationService {
	
	public boolean isValidUser(String username, String password) {
		if (username.equals("Azad") && password.equals("password"))
			return true;
		return false;
	}

}
