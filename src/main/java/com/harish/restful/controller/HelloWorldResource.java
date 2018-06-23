package com.harish.restful.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.harish.restful.entity.HelloWorldBean;

@RestController
public class HelloWorldResource {
	
	@GetMapping(path = "/hello-world")   // instead of @RequestMapping (method=RequestMethod.GET  we can use @GetMapping
	public  String helloWorld() {
		return "Hello World";
	}
	
	//Returns a POJO object -- note as long as the returning bean has getters the Spring boot Jackson combo will convert object to JSON and return it
	@GetMapping(path = "/hello-world-bean")   
	public  HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	//Path variables are extensively used in restful services to pass request parameters
	@GetMapping(path = "/hello-world/path-variable/{name}")   
	public  HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
	
}
