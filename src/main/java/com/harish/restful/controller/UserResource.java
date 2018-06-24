package com.harish.restful.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	//HATEOAS  - Hypermedia As The Engine Of Application State
	
	//retrieveAllUsers
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	
	@GetMapping("/users/{id}")
	public Resource<User>  retrieveUser(@PathVariable int id){
		User user = service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id : " + id);
		
		//"all-users", SERVER_PATH + "/users"
		//retrieveAllUsers   -- it will add a _links section to reply to give back
		Resource<User> resource = new Resource<User>(user);
		
		ControllerLinkBuilder linkSelf = 
				linkTo(methodOn(this.getClass()).retrieveUser(id));
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		resource.add(linkSelf.withRel("specific-user"));
				
		//HATEOAS
		return resource;
	}
	
	//@Valid will check that the incoming User object fields adhere to the validation rules defined in the entity 
	// and otherwise return 400 Bad Request
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User savedUser = service.save(user);
		
		// /user/{id}  savedUser.getId()
		URI location = ServletUriComponentsBuilder
									.fromCurrentRequest()
									.path("/{id}")
									.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id){
		User user = service.deleteById(id);
		if(user==null)
			throw new UserNotFoundException("id : " + id);
	}
	
	
}
