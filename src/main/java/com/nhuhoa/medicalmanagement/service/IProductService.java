package com.nhuhoa.medicalmanagement.service;

import java.util.List;

import com.nhuhoa.medicalmanagement.dto.MessageResponse;
import com.nhuhoa.medicalmanagement.dto.ProductDto;
import com.nhuhoa.medicalmanagement.entity.Product;


public interface IProductService {
	
	List<Product> findAll();
	
	Product findById(Long theId);
	
	MessageResponse createProduct(ProductDto theProductDto);
	
	MessageResponse updateProduct(Long theId, ProductDto theProductDto);
	
	void deleteProduct(Long theId);

	List<Product> search(String key);
	
	

}
