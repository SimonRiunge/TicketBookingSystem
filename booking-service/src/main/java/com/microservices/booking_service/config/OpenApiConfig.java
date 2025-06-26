package com.microservices.booking_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfig {
@Bean
public OpenAPI bookingServiceApi() {
	return new OpenAPI()
			.info(new io.swagger.v3.oas.models.info.Info()
					.title("Booking Service API")
					.description("Booking service documentation for ticketing system")
					.version("v1.0.0"));
}
}
