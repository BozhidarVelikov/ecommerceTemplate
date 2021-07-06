package com.bojidarET.ecommerceTemplate.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bojidarET.ecommerceTemplate.entities.Category;
import com.bojidarET.ecommerceTemplate.entities.Product;
import com.bojidarET.ecommerceTemplate.entities.ProductCategory;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>, CrudRepository<Product, Integer> {
	Page<Product> findAllByPrice(double price, Pageable pageable);
	
	Page<Product> findAllByProductCategory(ProductCategory category, Pageable pageable);
	
	Page<Product> findAll(Pageable pageable);
	
	Page<Product> findAllByIdIn(List<Integer> ids, Pageable pageable);
}
