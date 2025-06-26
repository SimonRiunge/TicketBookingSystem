package com.microservices.booking_service.client;

import com.microservices.booking_service.response.InventoryResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;


@HttpExchange
public interface InventoryHttpInterface {

    @GetExchange("/event/{eventId}")
    InventoryResponse getInventory(@PathVariable Long eventId);
}