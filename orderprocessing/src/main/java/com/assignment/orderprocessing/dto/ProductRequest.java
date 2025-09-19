package com.assignment.orderprocessing.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductRequest {
	
	@NotBlank(message = "Product name must not be blank")
    private String name;
	

	@NotNull(message = "Price is required")
	@DecimalMin(value = "0.01", message = "Price must be greater than 0")
	private Double price;

}
