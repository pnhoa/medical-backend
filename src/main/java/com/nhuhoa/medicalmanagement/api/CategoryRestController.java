package com.nhuhoa.medicalmanagement.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhuhoa.medicalmanagement.entity.CategoryEntity;
import com.nhuhoa.medicalmanagement.service.ICategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {
	
	private ICategoryService categoryService;

	@Autowired
	public CategoryRestController(ICategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	@GetMapping("")
	public ResponseEntity<List<CategoryEntity>> getAll(){
		
		List<CategoryEntity> categories = categoryService.findAll();
		
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryEntity> findById(@PathVariable("id") Long theId){
		CategoryEntity theCategory = categoryService.findById(theId);
		
		return new ResponseEntity<CategoryEntity>(theCategory, HttpStatus.OK);
	}

}
