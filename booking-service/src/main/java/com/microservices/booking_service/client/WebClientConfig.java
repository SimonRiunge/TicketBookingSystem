package com.microservices.booking_service.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {
	
	@Bean	
	public InventoryHttpInterface inventoryHttpInterface() {
		WebClient webClient=WebClient.builder().baseUrl("http://inventoryservice:8081/api/v1/inventory").build();
		WebClientAdapter adapter=WebClientAdapter.create(webClient);
		HttpServiceProxyFactory factory=HttpServiceProxyFactory.builderFor(adapter).build();
		
		InventoryHttpInterface service=factory.createClient(InventoryHttpInterface.class);
		
		return service;
	}
}
