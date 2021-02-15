package com.github.sats17.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.sats17.dao.HeroLayer;
import com.github.sats17.model.Heros;
import com.github.sats17.test.DefaultRaw;
import com.github.sats17.test.DefaultRawImpl;
import com.github.sats17.test.interfaceTesting;

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
	
	
	
	public void test() {
		interfaceTesting it = new DefaultRaw();
		interfaceTesting itR = new DefaultRawImpl();
		System.out.println("Default Raw extends result "+it.aggregate(1));
		System.out.println("Default RawImpl extends result "+itR.aggregate(10));
		System.out.println("Default RawImpl extends result "+itR.plus(10));
	}
}
