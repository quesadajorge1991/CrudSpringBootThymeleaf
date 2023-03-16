package com.springbootapplication.SpringBootApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootapplication.SpringBootApplication.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
