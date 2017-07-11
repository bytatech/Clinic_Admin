package com.lxisoft.byta.LogAspect;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;



@Component
public class DBConnector {

	@Autowired
	static JdbcTemplate temp;
	
	static DataSource dataSource;
	
	
	
	
	
	public  static Connection getDatabaseConnection() throws Exception {

		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/receptionistdb", "root", "root");
		
        return connection;
    }
}
