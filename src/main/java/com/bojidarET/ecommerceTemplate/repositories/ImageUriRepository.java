package com.bojidarET.ecommerceTemplate.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.bojidarET.ecommerceTemplate.entities.ImageUri;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ImageUriRepository extends CrudRepository<ImageUri, Integer> {
	public Iterable<ImageUri> findByProductIdIn(Set<Integer> ids); 
}
