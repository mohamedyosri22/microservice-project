package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.OrderMapper;
import com.spring.ecommerce.dto.OrderRequest;
import com.spring.ecommerce.dto.PurchaseRequest;
import com.spring.ecommerce.exception.BusinessException;
import com.spring.ecommerce.model.OrderLineRequest;
import com.spring.ecommerce.model.OrderLineService;
import com.spring.ecommerce.repository.OrderRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepo orderRepo;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    public Integer createOrder(OrderRequest request) {
        //check the customer
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(()->new BusinessException(String.format("customer not found with id %s",request.customerId())));

        //purchase the product --> product-ms RestTemplate
        productClient.purchaseProducts(request.products());

        //persist order
        var order = orderRepo.save(mapper.toOrder(request));

        //persist orderlines
        for(PurchaseRequest purchaseRequest: request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        //todo start payment process

        //send order confirmation --> notification-ms

        return null;
    }
}
