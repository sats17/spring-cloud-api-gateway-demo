package com.github.sats17.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.sats17.dao.HeroLayer;
import com.github.sats17.dao.VillanLayer;
import com.github.sats17.model.Heros;
import com.github.sats17.model.Response;
import com.github.sats17.model.Tag;
import com.github.sats17.model.Villans;

@RestController
@RequestMapping("/filters")
public class FilterController<E> {
	
	@Autowired
	HeroLayer herosLayer;
	
	@Autowired
	VillanLayer villansLayer;
	
	@GetMapping("/predefined")
	public Response getFilterResponse(@RequestHeader(value = "filters", required = true) String filter) {
		if(filter == "villan") {
			Tag<Villans> villans = new Tag<>();
			villans.setStatus("The call has return villans list");
			villans.setResponse(villansLayer.findAll());
			Response response = new Response();
			response.setTag(villans);
			return response;
		}
		Tag<Heros> heros = new Tag<>();
		heros.setStatus("The call has return heros list");
		heros.setResponse(herosLayer.findAll());
		Response response = new Response();
		response.setTag(heros);
		return response;
	}

}
