package com.springbootapplication.SpringBootApplication.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message = "El nombre no debe estar vacío")
	@Size(min = 2,message = "El nombre debe ser mayor de 2 caracteres")
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String deptName) {
		this.name = deptName;
	}

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department(String name) {
		super();
		this.name = name;
	}

	public Department(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Department(int id) {
		super();
		this.id = id;
	}
	
	
	
	
}
