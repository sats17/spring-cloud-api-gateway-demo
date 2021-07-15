package com.github.sats17.filters;

import java.net.URI;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class ChooseBackendFilter extends AbstractGatewayFilterFactory<ChooseBackendFilter.Config> {
	
	@Value("${users.host}")
	private String usersHost;
	
	@Value("${client.host}")
	private String clientHost;
	
	@Value("${customer.host}")
	private String customerHost;

	@Override
	public Config newConfig() {
		return new Config();
	}

	public static class Config {

	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			String path = exchange.getRequest().getPath().value();
			String[] segments = path.split("/");
			String backendpath = segments[segments.length - 1];
			String hostUri = "";
			String newPath = "";
			if (backendpath.equals("client")) {
				System.out.println("We are in client");
				newPath = "/client";
				hostUri = clientHost;

			} else if (backendpath.equals("users")) {
				System.out.println("We are in users");
				newPath = "/api/v1/information/2455/legacy";
				hostUri = usersHost;

			} else if (backendpath.equals("customer")) {
				System.out.println("We are in customer");
				newPath = "/customer";
				hostUri = customerHost;

			}
			System.out.println();
			URI uri = URI.create(hostUri);
			System.out.println(uri);
			
			ServerHttpRequest request = exchange.getRequest().mutate().path(newPath).uri(uri).build();
			System.out.println(request.getURI());
			exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, request.getURI());
			System.out.println(exchange);
			return chain.filter(exchange.mutate().request(request).build());
			
		};
	}
	
}
