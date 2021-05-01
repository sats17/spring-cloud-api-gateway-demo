package com.github.sats17.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import com.github.sats17.filters.PreFilters.Config;

import reactor.core.publisher.Mono;

@Component
public class TestFilter extends AbstractGatewayFilterFactory<TestFilter.Config> {
	
	public TestFilter() {
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
			return null;
			
		};
	}
}
