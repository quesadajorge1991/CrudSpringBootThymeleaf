package com.springbootapplication.SpringBootApplication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.SecurityFilterChain;

import com.springbootapplication.SpringBootApplication.Repository.EmployeeRepository;

@SpringBootTest
class ApplicationTests {

	@Autowired
	DataSource dataSource;

	@Autowired
	ApplicationContext applicationContext;

	SecurityFilterChain chain;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Test
	void contextLoads() throws SQLException {

	
		System.err.println(employeeRepository.findById(704).get().getDepartment().getName());
		
		

	}

}
