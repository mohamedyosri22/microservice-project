package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.*;
import com.spring.ecommerce.exception.BusinessException;
import com.spring.ecommerce.kafka.OrderProducer;
import com.spring.ecommerce.model.Order;
import com.spring.ecommerce.model.OrderLineRequest;
import com.spring.ecommerce.model.OrderLineService;
import com.spring.ecommerce.payment.PaymentClient;
import com.spring.ecommerce.repository.OrderRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepo orderRepo;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;
    public Integer createOrder(OrderRequest request) {
        //check the customer
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(()->new BusinessException(String.format("customer not found with id %s",request.customerId())));

        //purchase the product --> product-ms RestTemplate
        var PurchasedProducts = productClient.purchaseProducts(request.products());

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

        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                request.id(),
                request.reference(),
                customer
        );

        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        PurchasedProducts
                )
        );
        return order.getId();
    }

    public List<OrderResponse> findAllOrders(){
        return orderRepo.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id) {
        return orderRepo.findById(id)
                .map(mapper::fromOrder)
                .orElseThrow(()->new BusinessException(String.format("order with id %d not found",id)));

    }
}
