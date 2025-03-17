package com.spring.ecommerce.dto;

import com.spring.ecommerce.model.Order;
import com.spring.ecommerce.model.OrderLine;
import com.spring.ecommerce.model.OrderLineRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
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

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
          orderLine.getId(), orderLine.getQuantity()
        );

    }
}
