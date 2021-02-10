package com.github.sats17.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@GetMapping("/test")
	public String testPath() {
		return "customer test path response";
	}
	
	@GetMapping("")
	public String rootPath() {
		return "customer root path response";
	}
	
	@GetMapping("/test/test2")
	public String nestedTes() {
		return "customer nested path response";
	}
	
	@GetMapping("/singlePredicate")
	public String singlePredicateTest() {
		return "customer singlePredicate path response";
	}
	
	@GetMapping("/predicate/nestedPath")
	public String predicateNestedPathTest() {
		return "customer predicate nested path response";
	}
	
	
}
