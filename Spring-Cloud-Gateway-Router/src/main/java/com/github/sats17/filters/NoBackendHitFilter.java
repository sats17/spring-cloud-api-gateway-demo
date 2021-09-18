package com.github.sats17.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;


@Component
public class NoBackendHitFilter extends AbstractGatewayFilterFactory<NoBackendHitFilter.Config> {

	public static class Config {

	}
	
	@Override
	public Config newConfig() {
		return new Config();
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			Mono<String> resp = Mono.just("Hello from spring cloud gateway");
			DataBufferFactory dataBufferFactory = exchange.getResponse().bufferFactory();
			// Using exchange and returning response from here we are breaking the chain of GateWayFilterChain and 
			//returning our own mono response.
			return exchange.getResponse().writeWith(resp.map(r -> dataBufferFactory.wrap(r.toString().getBytes())));
		};
	}
	
}
