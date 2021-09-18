package com.github.sats17.filters;

import java.util.Optional;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class PreFilters extends AbstractGatewayFilterFactory<PreFilters.Config> {
	
	public PreFilters() {
		super(Config.class);
	}

	public static class Config {
		private String APIKEY;

		public String getAPIKEY() {
			return APIKEY;
		}

		public void setAPIKEY(String aPIKEY) {
			APIKEY = aPIKEY;
		}
	}
	
	WebClient webClient = WebClient.builder()
	        .baseUrl("http://localhost:8093")
	        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	        .build();

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			System.out.println("Request => "+exchange.getRequest().getHeaders());
			System.out.println("Test filters applied");
			 return chain.filter(exchange)
		    	      .then(Mono.fromRunnable(() -> {
		    	          ServerHttpResponse response = exchange.getResponse();
		    	          Optional.ofNullable(exchange.getRequest()
		    	            .getQueryParams()
		    	            .getFirst("locale"))
		    	            .ifPresent(qp -> {
		    	                String responseContentLanguage = response.getHeaders()
		    	                  .getContentLanguage()
		    	                  .getLanguage();

		    	                response.getHeaders()
		    	                  .add("Bael-Custom-Language-Header", responseContentLanguage);
		    	                });
		    	        }));
		};
	}

}
