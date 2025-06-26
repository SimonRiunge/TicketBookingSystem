package com.microservices.inventoryservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfig {
@Bean
public OpenAPI inventoryServiceConfig() {
	return new OpenAPI()
			.info(new io.swagger.v3.oas.models.info.Info()
					.title("Inventory Service API")
					.description("Inventory Service API for ticketing system")
					.version("v1.0.0"));
}
}
