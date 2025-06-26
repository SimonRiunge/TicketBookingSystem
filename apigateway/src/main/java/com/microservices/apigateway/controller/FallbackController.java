package com.microservices.apigateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
	
	//@GetMapping("/booking")
	@RequestMapping (value="/fallback/booking", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<String> bookingFallback() {
	    return ResponseEntity
	        .ok("Booking service is currently unavailable. Please try again later.");
	}
}
