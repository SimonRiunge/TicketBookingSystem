package com.microservices.inventoryservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="venue")
public class Venue {
	@Id
	private Long id;
	private String name;
	private String address;
	@Column(name = "total_capacity")
	private Long totalCapacity;
}
