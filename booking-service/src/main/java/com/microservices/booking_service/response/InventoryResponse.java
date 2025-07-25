package com.microservices.booking_service.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class InventoryResponse {
	private Long eventId;
	private String event;
	private Long capacity;
	private VenueResponse venue;
	private BigDecimal ticketPrice;
}
