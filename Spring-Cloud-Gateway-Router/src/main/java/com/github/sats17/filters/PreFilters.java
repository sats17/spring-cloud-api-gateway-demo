package com.github.sats17.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

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

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			System.out.println("Request => "+exchange.getRequest().getHeaders());
			System.out.println("Pre filters applied");
			ServerHttpRequest request = exchange.getRequest().mutate().header("api-key", config.getAPIKEY()).build();
			return chain.filter(exchange.mutate().request(request).build());	
		};
	}

}
