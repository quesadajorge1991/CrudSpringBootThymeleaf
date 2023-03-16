/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootapplication.SpringBootApplication.Entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
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
@Table(name = "group_authorities")
public class GroupAuthorities implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String authority;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JoinColumn(name = "group_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Groups groupId;

	public GroupAuthorities() {
	}

	public GroupAuthorities(String authority, Groups groupId) {
		this.authority = authority;
		this.groupId = groupId;
	}

	public GroupAuthorities(Integer id) {
		this.id = id;
	}

	public GroupAuthorities(Integer id, String authority) {
		this.id = id;
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

	public Groups getGroupId() {
		return groupId;
	}

	public void setGroupId(Groups groupId) {
		this.groupId = groupId;
	}

	public GroupAuthorities(Integer id, Groups groupId) {
		this.id = id;
		this.groupId = groupId;
	}
}
