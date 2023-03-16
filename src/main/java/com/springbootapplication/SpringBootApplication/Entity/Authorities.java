/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootapplication.SpringBootApplication.Entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author wason
 */
@Entity
@Table(name = "Authorities")
public class Authorities implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String authority;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username")
	private Users usernamee;

	public Authorities() {
	}

	public Authorities(Users usernamee) {
		this.usernamee = usernamee;
	}

	public Authorities(String authority, Users usernamee) {
		this.authority = authority;
		this.usernamee = usernamee;
	}

	public Authorities(Integer id) {
		this.id = id;
	}

	public Authorities(Integer id, String authority) {
		this.id = id;
		this.authority = authority;
	}

	public Authorities(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsernamee() {
		return usernamee;
	}

	public void setUsername(Users usernamee) {
		this.usernamee = usernamee;
	}

	public Authorities(Integer id, Users usernamee) {
		this.id = id;
		this.usernamee = usernamee;
	}

	public Authorities(Integer id, String authority, Users usernamee) {
		this.id = id;
		this.authority = authority;
		this.usernamee = usernamee;
	}
}
