package com.spring.ecommerce.kafka;

import com.spring.ecommerce.dto.Customer;
import com.spring.ecommerce.dto.Product;
import com.spring.ecommerce.model.PaymentMethod;
import org.bson.codecs.ObjectIdGenerator;

import java.math.BigDecimal;
import java.util.List;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,

        String customerLastName,

        String customerEmail
) {
}
