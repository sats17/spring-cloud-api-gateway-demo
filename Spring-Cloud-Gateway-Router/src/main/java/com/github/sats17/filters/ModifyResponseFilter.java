package com.github.sats17.filters;

import java.util.Optional;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class ModifyResponseFilter extends AbstractGatewayFilterFactory<ModifyResponseFilter.Config> {

	public static class Config {
		private String APIKEY;

		public String getAPIKEY() {
			return APIKEY;
		}

		public void setAPIKEY(String aPIKEY) {
			APIKEY = aPIKEY;
		}
	}

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
		    	                  .add("Test-header", responseContentLanguage);
		    	                });
		    	        }));
		};
		   
	
		}

}
