package com.nhuhoa.medicalmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nhuhoa.medicalmanagement.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT p FROM Product p WHERE lower(p.name) LIKE %?1%"
					+ " OR lower(p.code) LIKE %?1%" )
	List<Product> search( String key);

}
