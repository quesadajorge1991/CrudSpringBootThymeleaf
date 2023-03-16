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
@Table(name = "group_members")
public class GroupMembers implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	@JoinColumn(name = "group_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Groups groupId;

	public GroupMembers() {
	}

	public GroupMembers(String username, Groups groupId) {
		this.username = username;
		this.groupId = groupId;
	}

	public GroupMembers(Integer id) {
		this.id = id;
	}

	public GroupMembers(Integer id, String username) {
		this.id = id;
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Groups getGroupId() {
		return groupId;
	}

	public void setGroupId(Groups groupId) {
		this.groupId = groupId;
	}

	public GroupMembers(Integer id, String username, Groups groupId) {
		this.id = id;
		this.username = username;
		this.groupId = groupId;
	}

}
