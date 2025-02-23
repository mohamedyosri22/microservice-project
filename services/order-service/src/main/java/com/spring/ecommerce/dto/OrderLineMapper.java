package com.spring.ecommerce.dto;

import com.spring.ecommerce.model.Order;
import com.spring.ecommerce.model.OrderLine;
import com.spring.ecommerce.model.OrderLineRequest;

public class OrderLineMapper {
    public OrderLine toOrderline(OrderLineRequest request){
        return OrderLine.builder()
                .id(request.id())
                .order(
                         Order.builder()
                        .id(request.orderId())
                        .build())
                .Quantity(request.quantity())
                .build();
    }
}
