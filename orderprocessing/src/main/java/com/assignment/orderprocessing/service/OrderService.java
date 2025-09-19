package com.assignment.orderprocessing.service;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.assignment.orderprocessing.dto.OrderRequest;
import com.assignment.orderprocessing.entity.Order;
import com.assignment.orderprocessing.repository.OrderRepository;


@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;
    
    
    @Autowired
    @Lazy
    private OrderService self;

    @Async
    public void processOrder(Order order) {
        String threadName = Thread.currentThread().getName();
        logger.info("Started async processing for orderId={} on thread: {}", order.getId(), threadName);

        try {
//            Thread.sleep(2000); // Delay to observe "PENDING" in DB

            order.setStatus(Order.Status.PROCESSING);
            orderRepository.save(order);
            logger.info("Order ID {} status updated to PROCESSING on thread: {}", order.getId(), Thread.currentThread().getName());

            Thread.sleep(2000 + new Random().nextInt(1000)); // Simulated processing delay

            Order.Status finalStatus = new Random().nextBoolean() ?
                    Order.Status.COMPLETED : Order.Status.FAILED;
            order.setStatus(finalStatus);
            orderRepository.save(order);

            logger.info("Order ID {} status updated to {} on thread: {}", order.getId(), finalStatus, Thread.currentThread().getName());

        } catch (InterruptedException e) {
            order.setStatus(Order.Status.FAILED);
            orderRepository.save(order);
            logger.error("Order ID {} processing interrupted. Status set to FAILED. Thread: {}", order.getId(), Thread.currentThread().getName(), e);
        }
    }

    public Order createOrder(OrderRequest request) {
        productService.getProduct(request.getProductId());

        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setStatus(Order.Status.PENDING);
        order = orderRepository.save(order);

        logger.info("Order ID {} created with status PENDING on thread: {}", order.getId(), Thread.currentThread().getName());
        
        // Use proxy to trigger @Async
        self.processOrder(order);

        return order;
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> {
                logger.warn("Order ID {} not found", id);
                return new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
            });
    }
}
