package com.assignment.orderprocessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@SpringBootApplication
public class OrderProcessingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderProcessingApplication.class, args);
	}

}
