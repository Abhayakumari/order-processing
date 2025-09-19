package com.assignment.orderprocessing.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequest {
	@NotNull(message = "productId must not be null")
    private Long productId;
}