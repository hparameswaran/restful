package com.harish.restful.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.harish.restful.dao.UserDaoService;
import com.harish.restful.entity.User;
import com.harish.restful.exception.UserNotFoundException;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	
	//retrieveAllUsers
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id){
		User user = service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id : " + id);
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user){
		User savedUser = service.save(user);
		
		// /user/{id}  savedUser.getId()
		URI location = ServletUriComponentsBuilder
									.fromCurrentRequest()
									.path("/{id}")
									.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
}
