package com.microservices.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
	
@Bean
public RouteLocator customRouteLocator(RouteLocatorBuilder builder) { //Defines a Spring bean that returns a RouteLocator—used to programmatically define gateway routes
	return builder.routes() //Starts a fluent builder to declare routes.
			// Route for Inventory Service — no filters needed
			.route("inventory_service",r -> r.path("/api/v1/inventory/**") //Matches any path that starts with /api/v1/inventory/
					.uri("http://inventoryservice:8081")) //Forwards it to a service registered in Eureka as INVENTORY-SERVICE. lb:// tells Spring Cloud Gateway to use a load-balanced URI.
			// Route for Booking Service — has circuit breaker and query param
			.route("booking_service", r -> r
					.path("/api/v1/booking/**")
					
					.filters(filter->filter
					.addRequestParameter("source", "gateway")
					
					.circuitBreaker(config->config
						.setName("bookingCircuitBreaker")
						.setFallbackUri("forward:/fallback/booking")
						)
					)					
					.uri("http://booking-service:8082"))
			
			//OpenAPI/Swagger docs routes
			
			.route("inventory_service_api_docs", r -> r
				    .path("/docs/inventoryservice/{segment}")
				    .filters(f -> f.rewritePath("/docs/inventoryservice/(?<segment>.*)", "/${segment}"))
				    .uri("http://inventoryservice:8081"))
			
			.route("booking_service_api_docs", r -> r
				    .path("/docs/bookingservice/{segment}") // more general
				    .filters(f -> f.rewritePath("/docs/bookingservice/(?<segment>.*)", "/${segment}"))
				    .uri("http://booking-service:8082"))
			
			.route("swagger_ui", r -> r
				    .path("/", "/swagger-ui/**", "/swagger-ui.css", "/swagger-ui-bundle.js", "/swagger-initializer.js", "/swagger-ui-standalone-preset.js", "/swagger-config.yaml", "/favicon-*.png", "/index.css") // All requests to /swagger-ui/**
				    .uri("http://swagger-ui:8080") // Use the internal Docker hostname
				)
			
			.build(); //Builds the route definition
			
			
}

}
