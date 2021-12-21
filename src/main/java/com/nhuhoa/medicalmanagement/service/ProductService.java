package com.nhuhoa.medicalmanagement.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhuhoa.medicalmanagement.dto.MessageResponse;
import com.nhuhoa.medicalmanagement.dto.ProductDto;
import com.nhuhoa.medicalmanagement.entity.Product;
import com.nhuhoa.medicalmanagement.exception.ResourceNotFoundException;
import com.nhuhoa.medicalmanagement.repository.ProductRepository;

@Service
@Transactional
public class ProductService implements IProductService {
	
	private ProductRepository productRepository;
	
	private CategoryService categoryService;
	
	@Autowired
	public ProductService(ProductRepository productRepository, CategoryService categoryService) {
		super();
		this.productRepository = productRepository;
		this.categoryService = categoryService;
	}

	@Override
	public List<Product> findAll() {
		
		return productRepository.findAll();
	}

	@Override
	public Product findById(Long theId) throws ResourceNotFoundException {
		
		return productRepository.findById(theId).orElseThrow(() -> new ResourceNotFoundException("Not found product with ID=" + theId));
	}

	@Override
	public MessageResponse createProduct(ProductDto theProductDto) {
		Product theProduct = new Product();
		
		theProduct.setCode(theProductDto.getCode());
		theProduct.setName(theProductDto.getName());
		theProduct.setDescription(theProductDto.getDescription());
		theProduct.setPrice(theProductDto.getPrice());
		theProduct.setQuantity(theProductDto.getQuantity());
		theProduct.setCategory(categoryService.findByCode(theProductDto.getCategoryCode()));
		theProduct.setCreatedDate(new Date());
		theProduct.setCreatedBy("");
		
		productRepository.save(theProduct);
		
		return new MessageResponse("Create product successfully!");
	}

	@Override
	public MessageResponse updateProduct(Long theId, ProductDto theProductDto) throws ResourceNotFoundException {

		Optional<Product> theProduct = productRepository.findById(theId);
		
		if(!theProduct.isPresent()) {
			throw new ResourceNotFoundException("Not found product with ID=" + theId);
		} else {
			theProduct.get().setCode(theProductDto.getCode());
			theProduct.get().setName(theProductDto.getName());
			theProduct.get().setDescription(theProductDto.getDescription());
			theProduct.get().setPrice(theProductDto.getPrice());
			theProduct.get().setQuantity(theProductDto.getQuantity());
			theProduct.get().setCategory(categoryService.findByCode(theProductDto.getCategoryCode()));
			theProduct.get().setModifiedDate(new Date());
			theProduct.get().setModefiedBy("");
			productRepository.save(theProduct.get());
			
		}
		
		return new MessageResponse("Update product successfully!");
	}

	@Override
	public void deleteProduct(Long theId) throws ResourceNotFoundException {

		@SuppressWarnings("unused")
		Product theProduct = productRepository.findById(theId).orElseThrow(
				() -> new ResourceNotFoundException("Not found product with ID=" + theId));
		
		productRepository.delete(theProduct);
		
	}

	@Override
	public List<Product> search(String key) {
		
		key = key.toLowerCase();
		
		return productRepository.search(key);
	}

}
