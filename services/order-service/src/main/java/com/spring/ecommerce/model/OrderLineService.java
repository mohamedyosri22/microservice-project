package com.spring.ecommerce.model;

import com.spring.ecommerce.dto.OrderLineMapper;
import com.spring.ecommerce.dto.OrderLineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepo orderLineRepo;
    private final OrderLineMapper mapper;
    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = mapper.toOrderline(orderLineRequest);
        return orderLineRepo.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepo.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }


}
