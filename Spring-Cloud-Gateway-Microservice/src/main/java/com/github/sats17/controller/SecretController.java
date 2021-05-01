package com.github.sats17.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.sats17.dao.HeroLayer;
import com.github.sats17.dao.VillanLayer;
import com.github.sats17.model.Errors;
import com.github.sats17.model.Heros;
import com.github.sats17.model.Response;
import com.github.sats17.model.Tag;
import com.github.sats17.model.Villans;

@RestController
@RequestMapping("/secured")
public class SecretController {

	@Autowired
	HeroLayer herosLayer;

	@Autowired
	VillanLayer villansLayer;
	
	@Value("${apikey}")
	private String configuredApiKey;

	@GetMapping("/predefined")
	public ResponseEntity<Response> getFilterResponse(@RequestHeader(value = "filters", required = true) String filter,
			@RequestHeader(value = "api-key", required = true) String apiKey) {
		System.out.println("Request recevied for secret controller");
		if(!apiKey.equals(configuredApiKey)) {
			Errors error = new Errors();
			error.setCode(40001);
			error.setReason("Authentication failed, please try correct credentials");
			List<Errors> list = new ArrayList<>();
			list.add(error);
			Tag<Errors> tag = new Tag<>();
			tag.setResponse(list);
			tag.setStatus("Error occured");
			Response response = new Response();
			response.setTag(tag);
			ResponseEntity<Response> entity = new ResponseEntity<Response>(response, HttpStatus.UNAUTHORIZED);
			return entity;
		}
		HttpHeaders headers = new HttpHeaders();
		headers.set("resonseHeaderFromMicroserive", "HeaderValue");
		if (filter.equals("villans")) {
			Tag<Villans> villans = new Tag<>();
			villans.setStatus("The call has return villans list");
			villans.setResponse(villansLayer.findAll());
			Response response = new Response();
			response.setTag(villans);
			ResponseEntity<Response> entity = new ResponseEntity<Response>(response, headers, HttpStatus.OK);
			return entity;
		}
		Tag<Heros> heros = new Tag<>();
		heros.setStatus("The call has return heros list");
		heros.setResponse(herosLayer.findAll());
		Response response = new Response();
		response.setTag(heros);
		ResponseEntity<Response> entity = new ResponseEntity<Response>(response, headers, HttpStatus.OK);
		return entity;
	}

}
