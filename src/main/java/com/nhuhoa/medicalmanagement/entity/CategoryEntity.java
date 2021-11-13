package com.nhuhoa.medicalmanagement.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name= "category")
@JsonIgnoreProperties("products")
public class CategoryEntity extends BaseEntity {
	
	@Column(name = "code", columnDefinition = "varchar(50)", nullable = false)
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	@JsonIgnoreProperties("category")
	Set<ProductEntity> products;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public Set<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductEntity> products) {
		this.products = products;
	}

	public CategoryEntity() {
		super();
	}
	
	

}
