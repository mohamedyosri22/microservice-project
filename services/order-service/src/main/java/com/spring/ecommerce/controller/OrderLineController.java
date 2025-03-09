package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.OrderLineResponse;
import com.spring.ecommerce.model.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-line")
@RequiredArgsConstructor
public class OrderLineController {
    private final OrderLineService orderLineService;
    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(@PathVariable("order-id")Integer orderId){
        return new ResponseEntity<List<OrderLineResponse>>(orderLineService.findAllByOrderId(orderId), HttpStatus.FOUND);
    }
}
