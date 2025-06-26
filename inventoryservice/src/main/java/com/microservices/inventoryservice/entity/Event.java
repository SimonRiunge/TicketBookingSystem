package com.microservices.inventoryservice.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="event")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
@Column(name = "total_capacity")
private Long totalCapacity;
@Column(name = "left_capacity")
private Long left_capacity;
@Column(name = "ticket_price")
private BigDecimal ticketPrice;
@ManyToOne
@JoinColumn(name = "venue_id")
private Venue venue;
}
