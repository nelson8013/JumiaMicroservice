package com.hacknode.orderservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hacknode.orderservice.Model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
 
}
