package com.spring.ecommerce.model;

public record OrderLineRequest(
        Integer id,
        Integer orderId,
        Integer productId,
        double quantity


) {
}
