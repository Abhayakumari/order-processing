package com.assignment.orderprocessing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.orderprocessing.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}
