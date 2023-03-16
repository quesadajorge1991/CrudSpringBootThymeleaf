package com.springbootapplication.SpringBootApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootapplication.SpringBootApplication.Entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	
	
}
