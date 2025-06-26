package com.microservices.orderservice.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PutExchange;

@HttpExchange
public interface InventoryHttpInterface {
	
@PutExchange("/event/{eventId}/capacity/{capacity}")
public ResponseEntity<Void> updateInventory(@PathVariable Long eventId, @PathVariable Long capacity);

}
