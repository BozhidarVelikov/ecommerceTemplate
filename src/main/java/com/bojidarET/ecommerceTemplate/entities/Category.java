package com.bojidarET.ecommerceTemplate.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String category;
	
	@OneToMany(mappedBy="category")
	private List<ProductCategory> productCategory;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<ProductCategory> getProducts() {
		return productCategory;
	}

	public void setProducts(List<ProductCategory> productCategory) {
		this.productCategory = productCategory;
	}
}
