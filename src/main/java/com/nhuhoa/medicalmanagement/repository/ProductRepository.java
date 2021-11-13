package com.nhuhoa.medicalmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nhuhoa.medicalmanagement.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	
	@Query("SELECT p FROM ProductEntity p WHERE lower(p.name) LIKE %?1%"
					+ " OR lower(p.code) LIKE %?1%" )
	List<ProductEntity> search( String key);

}
