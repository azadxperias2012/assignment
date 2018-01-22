package com.neotechlabs.thrillio.dao;

import java.util.List;

import com.neotechlabs.thrillio.DataStore;
import com.neotechlabs.thrillio.entities.User;

public class UserDao {
	public List<User> getUsers() {
		return DataStore.getUsers();
	}
}
