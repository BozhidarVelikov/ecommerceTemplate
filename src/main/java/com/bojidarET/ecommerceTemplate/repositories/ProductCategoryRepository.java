package com.bojidarET.ecommerceTemplate.repositories;

import org.springframework.data.repository.CrudRepository;

import com.bojidarET.ecommerceTemplate.entities.ProductCategory;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Integer> {
	public Iterable<ProductCategory> findAllByCategoryId(Integer categoryId);
}
