package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.ProductPurchaseRequest;
import com.spring.ecommerce.dto.ProductPurchaseResponse;
import com.spring.ecommerce.dto.ProductRequest;
import com.spring.ecommerce.dto.ProductResponse;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest request){
        return new ResponseEntity<Integer>(productService.createProduct(request), HttpStatus.CREATED);
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> request) {
        return new ResponseEntity<List<ProductPurchaseResponse>>(productService.purchaseProducts(request),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable("product-id")Integer productId){
        return new ResponseEntity<ProductResponse>(productService.getById(productId),HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll (){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.FOUND);
    }





}
