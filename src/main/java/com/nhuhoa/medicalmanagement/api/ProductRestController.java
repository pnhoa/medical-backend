package com.nhuhoa.medicalmanagement.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhuhoa.medicalmanagement.dto.MessageResponse;
import com.nhuhoa.medicalmanagement.dto.ProductDto;
import com.nhuhoa.medicalmanagement.entity.Product;
import com.nhuhoa.medicalmanagement.service.IProductService;

@RestController
@RequestMapping("/api/products")
@PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
@CrossOrigin
public class ProductRestController {
	
	private IProductService productService;

	@Autowired
	public ProductRestController(IProductService productService) {
		super();
		this.productService = productService;
	}
	
	@GetMapping("")
	public ResponseEntity<List<Product>> findAll(){
		
		List<Product> theProducts = productService.findAll();
		
		return new ResponseEntity<>(theProducts, HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable("id") Long theId){
		
		Product theProduct = productService.findById(theId);
		
		return new ResponseEntity<Product>(theProduct, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Product>> searchProducts(@RequestParam(name="key", required = false) String key){
		
		List<Product> theProducts = productService.search(key);
		
		return new ResponseEntity<>(theProducts, HttpStatus.OK);
		
	}
	
	@PostMapping("")
	public ResponseEntity<MessageResponse> createProduct(@Valid @RequestBody ProductDto theProductDto){
		
		MessageResponse messageResponse = productService.createProduct(theProductDto);
		
		return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MessageResponse> updateProduct(@PathVariable("id") Long theId,
											@Valid @RequestBody ProductDto theProductDto){
		
		MessageResponse messageResponse = productService.updateProduct(theId, theProductDto);
		
		return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long theId){
		
		productService.deleteProduct(theId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	

}
