package com.microservices.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.inventoryservice.entity.Event;
@Repository
public interface EventRepository extends JpaRepository<Event, Long>{

}
