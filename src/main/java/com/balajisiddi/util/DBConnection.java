package com.balajisiddi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	Connection connection;
	
	public DBConnection(String dbUrl, String dbUser, String dbPwd) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		connection= DriverManager.getConnection(dbUrl, dbUser, dbPwd);
	}
	public Connection getConnection() {
		return connection;
	}
}
