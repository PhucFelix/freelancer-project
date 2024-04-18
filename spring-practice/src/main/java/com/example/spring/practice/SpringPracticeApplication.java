package com.example.spring.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class SpringPracticeApplication {

	public static void main(String[] args) throws SQLException {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/new_project?user=root&password=");
		// Statement st = conn.createStatement();
		// ResultSet rs = st.executeQuery( "select * from table" );

		System.out.println("Connected?");

		SpringApplication.run(SpringPracticeApplication.class, args);
	}

}
