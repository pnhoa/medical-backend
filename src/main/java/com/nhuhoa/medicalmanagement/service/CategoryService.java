package com.nhuhoa.medicalmanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhuhoa.medicalmanagement.entity.Category;
import com.nhuhoa.medicalmanagement.exception.ResourceNotFoundException;
import com.nhuhoa.medicalmanagement.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService implements ICategoryService {
	
	private CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}
	

	@Override
	public Map<String, String> findAllReturnMap() {
		
		Map<String, String> result = new HashMap<String, String>();
		List<Category> entities = categoryRepository.findAll();
		for(Category item: entities) {
			result.put(item.getCode(),item.getName());
		}
		return result;
	}


	@Override
	public List<Category> findAll() {
		
		return categoryRepository.findAll();
	}


	@Override
	public Category findById(Long theId) throws ResourceNotFoundException{
		
		return categoryRepository.findById(theId).orElseThrow(() -> new ResourceNotFoundException("Not found category with ID= " + theId));
	}


	@Override
	public Category findByCode(String theCode) {

		return categoryRepository.findByCode(theCode).orElseThrow(() -> new ResourceNotFoundException("Not found category with code= " + theCode));
	}
	

	

}
