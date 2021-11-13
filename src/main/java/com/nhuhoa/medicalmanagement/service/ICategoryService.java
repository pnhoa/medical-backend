package com.nhuhoa.medicalmanagement.service;

import java.util.List;
import java.util.Map;

import com.nhuhoa.medicalmanagement.entity.CategoryEntity;

public interface ICategoryService {
	
	Map<String, String> findAllReturnMap();
	
	List<CategoryEntity> findAll();
	
	CategoryEntity findById(Long theId);
	
	CategoryEntity findByCode(String theCode);
	

}
