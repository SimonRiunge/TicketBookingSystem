package com.microservices.orderservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.microservices.booking_service.event.BookingEvent;
import com.microservices.orderservice.client.InventoryHttpInterface;
import com.microservices.orderservice.entity.Order;
import com.microservices.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;
	private final InventoryHttpInterface inventoryServiceClient;
	
	
@KafkaListener(topics = "booking", groupId = "order-service")
public void orderEvent(BookingEvent bookingEvent) {
	log.info("Received order event: {}",bookingEvent);
	
	//Create Order object for DB
	Order order = createOrder(bookingEvent);
	orderRepository.saveAndFlush(order);
	
	//Update Inventory
	inventoryServiceClient.updateInventory(order.getEventId(),order.getTicketCount());
	log.info("Inventory updated for event: {}, less tickets: {}", order.getEventId(),order.getTicketCount());
	
}

private Order createOrder(BookingEvent bookingEvent) {
	return Order.builder()
			.customerId(bookingEvent.getUserId())
			.eventId(bookingEvent.getEventId())
			.ticketCount(bookingEvent.getTicketCount())
			.totalPrice(bookingEvent.getTotalPrice())
			.build();
}

}
