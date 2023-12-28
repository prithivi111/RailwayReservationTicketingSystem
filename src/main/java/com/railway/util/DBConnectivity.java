package com.railway.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.railway.DOA.TicketingDOAandOperations;

public class DBConnectivity {
	
	static Logger log = Logger.getLogger(DBConnectivity.class);
	
	public static Connection getConnection(Properties property) {
		Connection conn = null;
		try {
			String jdbcUrl = property.getProperty("url");
			String jdbcusername = property.getProperty("username");
			String jdbcpassword = property.getProperty("password");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(jdbcUrl, jdbcusername, jdbcpassword);
		} catch (Exception e){
			 log.error(e.getStackTrace());	
		}
	
		return conn;
	}

	
}
