package com.github.sats17.route;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutes {
	
	@Value("${customerHost}")
	private String customerHost;
	
	@Value("${clientHost}")
	private String clientHost;
	
	private String rootPath = "/";
	
	private String customerRootPath = "/customer";
	private String customerTestPath = "/customer/test";
	private String customerNestedTestPath = "/customer/test/test2";
	private String customerSinglePredicatePath = "/customer/*";
	private String customerNestedPredicatePath = "/customer/**";
	
	private String clientRootPath = "/client";
	private String clientTestPath = "/client/test";
	private String clientNestedTestPath = "/client/test/test2";
	private String clientSinglePredicatePath = "/client/*";
	private String clientNestedPredicatePath = "/client/**";
	private String preDefinedFilterPath = "/filters/predefined";
	private String preDefinedFilterPathWithQueryParam = "/filters/predefinedwithparam";
	
	private String preDefinedRewritePath = "/rewrite/filters";
	private String preDefinedRewritePathForQueryParam = "/rewrite/filters/queryparam";
	
	

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
					  /**
					   * Customer host routes
					   */
					  // Routes all incoming request that exact matches to rootPath to customerHost
					  .route(r -> r.path(rootPath).uri(customerHost).id("routeRootPath"))
					  // Routes all incoming request that exact matches to customerRootPath to customerHost
					  .route(r -> r.path(customerRootPath).uri(customerHost).id("routeCustomerRootPath"))
					  // Routes all incoming request that exact matches to customerTestPath to customerHost
					  .route(r -> r.path(customerTestPath).uri(customerHost).id("routeCustomerTestPath"))
					  // Routes all incoming request that exact matches after customerNestedTestPath to customerHost
					  .route(r -> r.path(customerNestedTestPath).uri(customerHost).id("routeCustomerNEstedTestPath"))
					  // Routes all incoming request that matches after customerSinglePredicatePath to customerHost
					  /*
					   *  More on this if incoming request to gateway is "/customer/wrongPath" then this route will 
					   *  pick request and route it to customerHost
					   */
					  .route(r -> r.path(customerSinglePredicatePath).uri(customerHost).id("routeSinglePrediccateTestPath"))
					  // Routes all incoming request that matches after customerNestedPredicatePath to customerHost
					  /*
					   * Above SinglePredicate request cannot get match for request /customer/wrongpath/nestedwrongpath,
					   * hence this customerNestedPredicatePath will match all nested routes after customer and route
					   * the same to customerHost
					   */
					  .route(r -> r.path(customerNestedPredicatePath).uri(customerHost).id("routeNestedPredicateTestPath"))
					  
					  
					 
					  /**
					   * Client host routes
					   */
					  // Routes all incoming request that exact matches to rootPath to clientrHost
					  .route(r -> r.path(rootPath).uri(clientHost).id("routeRootPath"))
					  // Routes all incoming request that exact matches to clientRootPath to clientHost
					  .route(r -> r.path(clientRootPath).uri(clientHost).id("routeclientRootPath"))
					  // Routes all incoming request that exact matches to clientTestPath to clientHost
					  .route(r -> r.path(clientTestPath).uri(clientHost).id("routeclientTestPath"))
					  // Routes all incoming request that exact matches after clientNestedTestPath to clientHost
					  .route(r -> r.path(clientNestedTestPath).uri(clientHost).id("routeclientNEstedTestPath"))
					  // Routes all incoming request that matches after clientSinglePredicatePath to clientHost
					  /*
					   *  More on this if incoming request to gateway is "/client/someOtherPath" then this route will 
					   *  pick request and route it to clientHost
					   */
					  .route(r -> r.path(clientSinglePredicatePath).uri(clientHost).id("routeclientSinglePrediccateTestPath"))
					  // Routes all incoming request that matches after clientNestedPredicatePath to clientHost
					  /*
					   * Above SinglePredicate request cannot get match for request /customer/someOther/nestedOtherpath,
					   * hence this clientNestedPredicatePath will match all nested routes after client and route
					   * the same to clientHost
					   */
					  .route(r -> r.path(clientNestedPredicatePath).uri(clientHost).id("routeclientNestedPredicateTestPath"))
					  /**
					   * Route fetched heros by default
					   */
					  .route("filter",r -> r.path(preDefinedFilterPath)
							  				.filters(fn -> fn.addRequestHeader("filters", "heros"))
							  				.uri(clientHost))
					  /*
					   * Convert requested path with different downstream path
					   */
					  .route("rewritepath", r -> r.path(preDefinedRewritePath)
							  					  .filters(fn -> fn.rewritePath(preDefinedRewritePath, preDefinedFilterPath))
							  					  .uri(clientHost))
					  
					  .route("rewritepathwithqueryparam", r -> r.path(preDefinedRewritePathForQueryParam)
		  					  .filters(fn -> fn.rewritePath(preDefinedRewritePathForQueryParam, preDefinedFilterPathWithQueryParam))
		  					  .uri(clientHost))
					  .build();
					 
	}

}
