package com.nhuhoa.medicalmanagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "role")
@JsonIgnoreProperties("users")
public class Role extends BaseEntity {

	@Column(name = "code", unique = true, nullable = false)
	private String code;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	@JsonIgnoreProperties("roles")
	private List<User> users = new ArrayList<User>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}


}
