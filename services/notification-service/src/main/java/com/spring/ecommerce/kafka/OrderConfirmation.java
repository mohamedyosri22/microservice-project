package com.spring.ecommerce.kafka;

import com.spring.ecommerce.dto.Customer;
import com.spring.ecommerce.dto.Product;
import com.spring.ecommerce.model.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
