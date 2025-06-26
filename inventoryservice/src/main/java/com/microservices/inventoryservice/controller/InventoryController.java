package com.microservices.inventoryservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.inventoryservice.entity.Event;
import com.microservices.inventoryservice.entity.Venue;
import com.microservices.inventoryservice.response.EventInventoryResponse;
import com.microservices.inventoryservice.response.VenueInventoryResponse;
import com.microservices.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {
	private final InventoryService inventoryService;
//Get all the events
@GetMapping("/events")
public List<EventInventoryResponse> inventoryGetAllEvents(){
	return inventoryService.getAllEvents();
}

@GetMapping("/venue/{venueId}")
public VenueInventoryResponse inventoryByVenueId(@PathVariable Long venueId) {
	return inventoryService.getVenueInformation(venueId);
}

@PostMapping
public Event addEvent(@RequestBody Event event)
{
	return inventoryService.createNewEvent(event);
}

@PostMapping("/venue")
public Venue addVenue(@RequestBody Venue event)
{
	return inventoryService.createNewVenue(event);
}

@GetMapping("/event/{eventId}")
public EventInventoryResponse eventInventory(@PathVariable Long eventId){
	return inventoryService.getEventInventory(eventId);
}

@PutMapping("/event/{eventId}/capacity/{capacity}")
public ResponseEntity<Void> updateEventCapacity(@PathVariable Long eventId, @PathVariable("capacity") Long ticketsBooked){
	inventoryService.updateEventCapacity(eventId,ticketsBooked);
	return ResponseEntity.ok().build();
}

}
