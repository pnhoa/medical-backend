package com.nhuhoa.medicalmanagement.service;

import java.util.List;
import java.util.Map;

import com.nhuhoa.medicalmanagement.entity.Role;

public interface IRoleService {

	public Map<String, String> findAllByMap();
	
	public List<Role> findAll();
	
	public Role findById(Long theId);
	
	public Role findByCode(String code);
	
}
