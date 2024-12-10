package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.CustomerMapper;
import com.spring.ecommerce.dto.CustomerRequest;
import com.spring.ecommerce.dto.CustomerResponse;
import com.spring.ecommerce.exception.CustomerNotFoundException;
import com.spring.ecommerce.model.Customer;
import com.spring.ecommerce.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerMapper mapper;

    //separating the data from the request and response
    public String createCustomer(CustomerRequest request) {
        var customer = customerRepo.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public String updateCustomer(CustomerRequest request) {
        Customer dbCustomer = customerRepo.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update customer:: No customer found with the provided id %s"
                                , request.id())
                ));
        dbCustomer.setFirstname(request.firstname());
        dbCustomer.setLastname(request.lastname());
        dbCustomer.setEmail(request.email());
        dbCustomer.setAddress(request.address());

        customerRepo.save(dbCustomer);
        return dbCustomer.getId();
    }

    //separating the data from the request and response
    public List<CustomerResponse> returnAllCustomers() {
        return customerRepo.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean ifCustomerExits(String customerId) {
        return customerRepo.findById(customerId).isPresent();
    }

    //separating the data from the request and response
    public CustomerResponse getCustomer(String customerId) {
        return customerRepo.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(format("No Customer found with the given id %s" +
                        " , please enter a valid id", customerId)));

    }

    public String deleteCustomer(String customerId) {
        customerRepo.deleteById(customerId);
        return format("Customer with Id %s deleted Successfully", customerId);
    }
}
