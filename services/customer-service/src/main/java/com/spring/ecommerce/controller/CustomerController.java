package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.CustomerRequest;
import com.spring.ecommerce.dto.CustomerResponse;
import com.spring.ecommerce.model.Customer;
import com.spring.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return new ResponseEntity<String>(customerService.createCustomer(request)
                , HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestBody @Valid CustomerRequest request) {
        return new ResponseEntity<String>(customerService.updateCustomer(request)
                , HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return new ResponseEntity<List<CustomerResponse>>(customerService.returnAllCustomers()
                , HttpStatus.OK
        );
    }

    @GetMapping("/exits/{customer-id}")
    public ResponseEntity<Boolean> ifCustomerExits(@PathVariable("customer-id")
                                                   String CustomerId) {
        return new ResponseEntity<>(customerService.ifCustomerExits(CustomerId),
                HttpStatus.OK);
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("customer-id")
                                                        String customerId) {
        return new ResponseEntity<CustomerResponse>(customerService.getCustomer(customerId),
                HttpStatus.OK);
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("customer-id")
                                                 String customerId) {
        return new ResponseEntity<String>(customerService.deleteCustomer(customerId)
                , HttpStatus.OK);
    }


}
