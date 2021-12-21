package com.nhuhoa.medicalmanagement.service;

import java.util.List;
import java.util.Map;

import com.nhuhoa.medicalmanagement.entity.Category;

public interface ICategoryService {
	
	Map<String, String> findAllReturnMap();
	
	List<Category> findAll();
	
	Category findById(Long theId);
	
	Category findByCode(String theCode);
	

}
