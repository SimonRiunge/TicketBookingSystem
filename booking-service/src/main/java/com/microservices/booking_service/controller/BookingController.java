package com.microservices.booking_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.booking_service.request.BookingRequest;
import com.microservices.booking_service.response.BookingResponse;
import com.microservices.booking_service.service.BookingService;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {
	
	private final BookingService bookingService;
	
@PostMapping(consumes = "application/json", produces = "application/json", path = "/booking")
public BookingResponse createBooking(@RequestBody BookingRequest request) {
	return bookingService.createNewBooking(request);
}


}
