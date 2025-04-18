package com.spring.ecommerce.dto;

import com.spring.ecommerce.model.Order;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderMapper {
    public Order toOrder(OrderRequest request){
        return Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
