package com.spring.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Product name is required !")
        String name,
        @NotNull(message = "Product description is required !")
        String description,
        @Positive(message = "Quantity should be positive !")
        Double availableQuantity,
        @Positive(message = "Quantity should be positive")
        BigDecimal price,
        @NotNull(message = "Product category is required")
        Integer categoryId
) {
}
