package com.github.sats17.filters;

import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
			System.out.println("Pre filters applied");
			ServerHttpRequest request = exchange.getRequest().mutate().header("api-key", config.getAPIKEY()).build();
			Mono<String> resp = this.webClient.get()
	        .uri("/client")
	        .exchange()
	        .flatMap(response -> {
	        	System.out.println("Response from resp=>"+response);
	        	return response.bodyToMono(String.class);
	        });
//			resp.map(t -> {
//				System.out.println("Inside map"+t);
//				return t;
//			});
			DataBufferFactory dataBufferFactory = exchange.getResponse().bufferFactory();
			return exchange.getResponse().writeWith(resp.map(r -> {
				try {
					return dataBufferFactory.wrap(new ObjectMapper().writeValueAsBytes(r.getBytes()));
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return dataBufferFactory.wrap(new byte[0]);
			}));
		};
	}

}
