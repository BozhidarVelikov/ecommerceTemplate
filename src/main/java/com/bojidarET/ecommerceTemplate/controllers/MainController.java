package com.bojidarET.ecommerceTemplate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bojidarET.ecommerceTemplate.entities.User;
import com.bojidarET.ecommerceTemplate.repositories.UserRepository;

@Controller
@RequestMapping(path="/")
public class MainController {
	@Autowired
	private UserRepository userRepository;
	
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
	public String hello() {
		return "index";
		
	}
}
