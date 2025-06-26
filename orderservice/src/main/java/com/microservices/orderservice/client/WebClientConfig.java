package com.microservices.orderservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {
@Value("${inventory.service.url}")
private String inventoryServiceUrl;

	@Bean
	public InventoryHttpInterface httpInterface() {
		WebClient webClient=WebClient.builder().baseUrl(inventoryServiceUrl).build();
		WebClientAdapter adapter= WebClientAdapter.create(webClient);
		HttpServiceProxyFactory factory=HttpServiceProxyFactory.builderFor(adapter).build();
		InventoryHttpInterface service = factory.createClient(InventoryHttpInterface.class);
		return service;
	}
	
}
