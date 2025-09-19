package com.assignment.orderprocessing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.orderprocessing.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}
