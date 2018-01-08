package com.neotechlabs.thrillio.dao;

import com.neotechlabs.thrillio.DataStore;
import com.neotechlabs.thrillio.entities.User;

public class UserDao {
	public User[] getUsers() {
		return DataStore.getUsers();
	}
}
