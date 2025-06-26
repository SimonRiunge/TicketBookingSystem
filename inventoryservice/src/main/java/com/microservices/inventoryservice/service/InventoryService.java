package com.microservices.inventoryservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservices.inventoryservice.entity.Event;
import com.microservices.inventoryservice.entity.Venue;
import com.microservices.inventoryservice.repository.EventRepository;
import com.microservices.inventoryservice.repository.VenueRepository;
import com.microservices.inventoryservice.response.EventInventoryResponse;
import com.microservices.inventoryservice.response.VenueInventoryResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
private final VenueRepository venueRepository;
private final EventRepository eventRepository;

	public List<EventInventoryResponse> getAllEvents() {
		final List<Event> eventsList=eventRepository.findAll();
		return eventsList.stream()
				.map(event->EventInventoryResponse.builder()
						.event(event.getName())
						.capacity(event.getLeft_capacity())
						.venue(event.getVenue())
						.build()).toList();
	}

	public VenueInventoryResponse getVenueInformation(Long venueId) {
		final Venue venue=venueRepository.findById(venueId).orElse(null);
		return VenueInventoryResponse.builder()
				.venueId(venue.getId())
				.venueName(venue.getName())
				.totalCapacity(venue.getTotalCapacity())
				.build();
		
	}
	
	public Event createNewEvent(Event event) {
		Event createdEvent=eventRepository.save(event);
		return createdEvent;
	}
	
	public Venue createNewVenue(Venue venue) {
		Venue createdVenue=venueRepository.save(venue);
		return createdVenue;
	}

	public EventInventoryResponse getEventInventory(Long eventId) {
		Event event=eventRepository.findById(eventId).orElse(null);
		if(event==null) {
			throw new RuntimeException("Event not found");
		}
		else {
			return EventInventoryResponse.builder()
					.capacity(event.getLeft_capacity())
					.event(event.getName())
					.venue(event.getVenue())
					.eventId(event.getId())
					.ticketPrice(event.getTicketPrice())
					.build();
		}
	}

	public void updateEventCapacity(Long eventId, Long ticketsBooked) {
		final Event event=eventRepository.findById(eventId).orElse(null);
		event.setLeft_capacity(event.getLeft_capacity() - ticketsBooked);
		eventRepository.saveAndFlush(event);
		log.info("Updated event capacity for eventId: {} with tickets booked: {}", eventId, ticketsBooked);
	}
	
}
