package com.spring.ecommerce.dto;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "Product id is required")
        Integer productId,
        @NotNull(message = "quantity id is required")
        double quantity
) {
}
