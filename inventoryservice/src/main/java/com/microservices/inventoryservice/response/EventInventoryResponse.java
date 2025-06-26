package com.microservices.inventoryservice.response;

import java.math.BigDecimal;

import com.microservices.inventoryservice.entity.Venue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventInventoryResponse {
	private Long eventId;
	private String event;
	private Long capacity;
	private Venue venue;
	private BigDecimal ticketPrice;
}
