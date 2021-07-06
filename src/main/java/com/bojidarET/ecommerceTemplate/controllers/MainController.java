package com.bojidarET.ecommerceTemplate.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bojidarET.ecommerceTemplate.entities.Category;
import com.bojidarET.ecommerceTemplate.entities.ImageUri;
import com.bojidarET.ecommerceTemplate.entities.Product;
import com.bojidarET.ecommerceTemplate.entities.ProductCategory;
import com.bojidarET.ecommerceTemplate.entities.User;
import com.bojidarET.ecommerceTemplate.repositories.CategoryRepository;
import com.bojidarET.ecommerceTemplate.repositories.ImageUriRepository;
import com.bojidarET.ecommerceTemplate.repositories.ProductCategoryRepository;
import com.bojidarET.ecommerceTemplate.repositories.ProductRepository;
import com.bojidarET.ecommerceTemplate.repositories.UserRepository;

@Controller
@RequestMapping(path="/")
public class MainController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ImageUriRepository imageUriRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	@PostMapping(path="/user-add")
	public @ResponseBody String addNewUser (@RequestParam String fName,
	@RequestParam String lName, @RequestParam String email, @RequestParam String password) {
		
		User u = new User();
		u.setFirstName(fName);
		u.setLastName(lName);
		u.setEmail(email);
		u.setPassword(password);
		userRepository.save(u);
		return "Saved";
	}
	
	@GetMapping(path="/user-all")
	public @ResponseBody Iterable<User> getAllUsers() {
		
		return userRepository.findAll();
	}
	
	@GetMapping(path="/")
	public String home(Model model) {
		Pageable pageRequest = PageRequest.of(0, 4);
		
		// TODO: Products should be selected from featured table or a column should be added for featured products.
		Page<Product> products = productRepository.findAll(pageRequest);
		
		// Take the IDs of all the products from above 
		Set<Integer> ids = new HashSet<>();
		ArrayList<Product> productList = new ArrayList<>(products.getContent());
		for(int i = 0; i < productList.size(); i++) {
			ids.add(productList.get(i).getId());
		}
		
		// Get the images of all the products from above
		ArrayList<ImageUri> uris = (ArrayList<ImageUri>) imageUriRepository.findByProductIdIn(ids);
		for(int i = 0; i < uris.size(); i++) {
			if(!uris.get(i).isMainImage()) {
				uris.remove(uris.get(i));
				i--;
			}
		}
		model.addAttribute("featuredUris", uris);
		
		// Get all the categories
		ArrayList<Category> categories = (ArrayList<Category>) categoryRepository.findAll();
		model.addAttribute("categories", categories);
		
		return "index";
		
	}
	
	@GetMapping(path="/shop")
	public String shop(Model model, @RequestParam(required=false) Integer page, @RequestParam(required=false) String category) {
		// Select page
		if(page == null)
			page = 0;
		
		// Filter by category
		Category productsCategory = null;
		if(category != null) {
			productsCategory = (Category)((ArrayList)categoryRepository.findByCategory(category)).get(0);
		} else {
			productsCategory = (Category)((ArrayList)categoryRepository.findAll()).get(0);
		}
		
		ArrayList<ProductCategory> productCategories = (ArrayList)productCategoryRepository.findAllByCategoryId(productsCategory.getId());
		ArrayList<Integer> productIds = new ArrayList<>();
		for(int i = 0; i < productCategories.size(); i++) {
			productIds.add(productCategories.get(i).getProduct().getId());
		}
		
		Pageable pageRequest = PageRequest.of(page, 12);
		
		// Get the specific page of products with specific category
		Page<Product> products = productRepository.findAllByIdIn(productIds, pageRequest);
		
		// Take the IDs of all the products from above 
		Set<Integer> ids = new HashSet<>();
		ArrayList<Product> productList = new ArrayList<>(products.getContent());
		for(int i = 0; i < productList.size(); i++) {
			ids.add(productList.get(i).getId());
		}

		// Get the images of all the products from above
		ArrayList<ImageUri> uris = (ArrayList<ImageUri>) imageUriRepository.findByProductIdIn(ids);
		for(int i = 0; i < uris.size(); i++) {
			if(!uris.get(i).isMainImage()) {
				uris.remove(uris.get(i));
				i--;
			}
		}
		model.addAttribute("featuredUris", uris);
		
		// Get all the categories
		ArrayList<Category> categories = (ArrayList<Category>) categoryRepository.findAll();
		model.addAttribute("categories", categories);
		
		// FOR VISUALISATION PURPOSE
		
		// Get total number of pages
		model.addAttribute("pages", products.getTotalPages());
		
		// Get current page
		model.addAttribute("currentPage", page);
		
		// Get current category
		model.addAttribute("activeCategory", productsCategory);
		
		// END OF FOR VISUALISATION PURPOSE
		
		return "shop";		
	}
	
	@GetMapping(path="/product/{id}")
	public String product(Model model, @PathVariable Integer id) {
		return "product";
	}
	
}
