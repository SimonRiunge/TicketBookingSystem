package com.microservices.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.orderservice.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
