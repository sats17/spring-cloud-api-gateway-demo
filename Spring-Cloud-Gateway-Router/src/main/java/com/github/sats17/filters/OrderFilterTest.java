package com.github.sats17.filters;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class OrderFilterTest implements GatewayFilter, Ordered {
	
	@Value("${users.host}")
	private String usersHost;
		
	@Value("${client.host}")
	private String clientHost;
	
	@Value("${customer.host}")
	private String customerHost;

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1001;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
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
			newPath = "/users";
			hostUri = usersHost;

		} else if (backendpath.equals("customer")) {
			System.out.println("We are in customer");
			newPath = "/diagnostic/healthz";
			hostUri = "https://my-intplus-rest-info.ingress.dev.vet-dev.digitalecp.mcd.com";

		}
		String marketId = exchange.getRequest().getHeaders().getFirst("mcd-marketid");
		String newUrl = hostUri + newPath;
		System.out.println(newUrl);
		System.out.println("Before ->"+exchange.getAttributes().get(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR));
		
		exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, URI.create(newUrl));
		System.out.println("After ->"+exchange.getAttributes());
		return chain.filter(exchange);
	}

}
