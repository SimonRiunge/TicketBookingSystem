package com.microservices.booking_service.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.microservices.booking_service.client.InventoryHttpInterface;
import com.microservices.booking_service.entity.Customer;
import com.microservices.booking_service.event.BookingEvent;
import com.microservices.booking_service.repository.CustomerRepository;
import com.microservices.booking_service.request.BookingRequest;
import com.microservices.booking_service.response.BookingResponse;
import com.microservices.booking_service.response.InventoryResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {
	private final CustomerRepository customerRepository;
	private final InventoryHttpInterface inventoryHttpInterface;
	private final KafkaTemplate<String, BookingEvent> kafkaTemplate; 
	private Logger logger=LoggerFactory.getLogger(BookingService.class);

	public BookingResponse createNewBooking(BookingRequest request) {
		Customer customer=customerRepository.findById(request.getUserId()).orElse(null);
		if(customer==null) {
			throw new RuntimeException("customer not found");
		}
		final InventoryResponse inventoryResponse=inventoryHttpInterface.getInventory(request.getEventId());
		logger.info("Inventory service Response " + inventoryResponse);
		if(inventoryResponse.getCapacity() < request.getTicketCount()) {
			throw new RuntimeException("Not Enough Inventory");
		}
		
		//create booking
		final BookingEvent bookingEvent=createBookingRequest(request,customer,inventoryResponse);
		kafkaTemplate.send("booking",bookingEvent);
		logger.info("Booking sent to Kafka: {}", bookingEvent);
		
		//send booking to Order Service on a Kafka topic
		
	return	BookingResponse.builder()
			.userId(bookingEvent.getUserId())
			.eventId(bookingEvent.getEventId())
			.ticketCount(bookingEvent.getTicketCount())
			.totalPrice(bookingEvent.getTotalPrice())
			.build();
	}
	
	private BookingEvent createBookingRequest(final BookingRequest request, final Customer customer, final InventoryResponse inventoryResponse) {
		return BookingEvent.builder()
				.userId(customer.getId())
				.eventId(request.getEventId())
				.ticketCount(request.getTicketCount())
				.totalPrice(inventoryResponse.getTicketPrice().multiply(BigDecimal.valueOf(request.getTicketCount())) )
				.build();
	}

}
