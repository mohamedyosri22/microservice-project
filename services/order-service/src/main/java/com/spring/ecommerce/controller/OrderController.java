package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.OrderRequest;
import com.spring.ecommerce.dto.OrderResponse;
import com.spring.ecommerce.model.Order;
import com.spring.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest request){
        return new ResponseEntity<>(orderService.createOrder(request),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        return new ResponseEntity<>(orderService.findAllOrders(),HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable("id")Integer id){
        return new ResponseEntity<OrderResponse>(orderService.findById(id),HttpStatus.FOUND);
    }
}
