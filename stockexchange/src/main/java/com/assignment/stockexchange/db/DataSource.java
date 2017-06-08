package com.assignment.stockexchange.db;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSource {
	
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String M_CONN_STRING =
			"jdbc:mysql://localhost:3306/exchange";
	
	private static DataSource dataSource;
	private BasicDataSource basicDataSource;
	
	private DataSource() throws IOException, SQLException, PropertyVetoException {
		basicDataSource = new BasicDataSource();
		
		basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		basicDataSource.setUsername(USERNAME);
		basicDataSource.setPassword(PASSWORD);
		basicDataSource.setUrl(M_CONN_STRING);
	}
	
	public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
		if (dataSource == null) {
			dataSource = new DataSource();
		}
		return dataSource;
	}

	public Connection getConnection() throws SQLException {
		return this.basicDataSource.getConnection();
	}
}
