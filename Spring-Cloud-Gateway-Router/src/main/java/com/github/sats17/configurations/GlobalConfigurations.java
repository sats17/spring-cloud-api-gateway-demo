//package com.github.sats17.configurations;
//
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.server.ServerWebExchange;
//
//import reactor.core.publisher.Mono;
//
//@Configuration
//public class GlobalConfigurations implements GlobalFilter {
//
//	@Bean
//	@Order(-1)
//	public GlobalFilter basicGlobalFilter() {
//		System.out.println("This will print at the bean creation team(At the start of application)");
//	    return (exchange, chain) -> {
//	    	System.out.println("Default response headers => "+exchange.getResponse().getHeaders());
//	        System.out.println("Default request headers => "+exchange.getRequest().getHeaders());
//	        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//	        	System.out.println(exchange);
//	        	System.out.println("Response headers from microservice =>"+exchange.getResponse().getHeaders());
//	        }));
//	    };
//	}
//
//	@Override
//	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//}
