package com.bojidarET.ecommerceTemplate.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.bojidarET.ecommerceTemplate.entities.Category;
import com.bojidarET.ecommerceTemplate.entities.ImageUri;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
	public Iterable<Category> findByCategory(String category); 
}
