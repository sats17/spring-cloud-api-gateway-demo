package com.github.sats17.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

	@GetMapping("/test")
	public String testPath() {
		return "client test path response";
	}
	
	@GetMapping("")
	public String rootPath() {
		return "client root path response";
	}
	
	@GetMapping("/test/test2")
	public String nestedTes() {
		return "client nested path response";
	}
	
	@GetMapping("/singlePredicate")
	public String singlePredicateTest() {
		return "client singlePredicate path response";
	}
	
	@GetMapping("/predicate/nestedPath")
	public String predicateNestedPathTest() {
		return "client predicate nested path response";
	}
	
}
