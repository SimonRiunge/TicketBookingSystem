package com.microservices.booking_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VenueResponse {
	private Long venueId;
	private String venueName;
	private Long totalCapacity;
}
