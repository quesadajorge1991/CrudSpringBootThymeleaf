/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootapplication.SpringBootApplication.Entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author wason
 */
@Entity
@Table(name = "groups")
public class Groups implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String groupName;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "groupId")
	private List<GroupMembers> groupMembersList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "groupId")
	private List<GroupAuthorities> groupAuthoritiesList;

	public Groups() {
	}

	public Groups(String groupName) {
		this.groupName = groupName;
	}

	public Groups(Integer id) {
		this.id = id;
	}

	public Groups(Integer id, String groupName) {
		this.id = id;
		this.groupName = groupName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	// @XmlTransient
	public List<GroupMembers> getGroupMembersList() {
		return groupMembersList;
	}

	public void setGroupMembersList(List<GroupMembers> groupMembersList) {
		this.groupMembersList = groupMembersList;
	}

	// @XmlTransient
	public List<GroupAuthorities> getGroupAuthoritiesList() {
		return groupAuthoritiesList;
	}

	public void setGroupAuthoritiesList(List<GroupAuthorities> groupAuthoritiesList) {
		this.groupAuthoritiesList = groupAuthoritiesList;
	}

}
