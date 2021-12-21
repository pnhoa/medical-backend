package com.nhuhoa.medicalmanagement.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.nhuhoa.medicalmanagement.entity.Category;


public class ProductDto extends AbstractDto {
	
	@NotNull(message = "Please enter code")
	private String code;
	
	@NotNull(message = "Please enter name")
	private String name;
	
	private String description;
	
	@NotNull(message = "Please enter price")
	private BigDecimal price;
	
	@NotNull(message = "Please enter price")
	private int quantity;
	
	private Category category;
	
	private String categoryCode;
	
	private String categoryName;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public ProductDto() {
		super();
	}
	
	

}
