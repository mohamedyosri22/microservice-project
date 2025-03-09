package com.spring.ecommerce.dto;

import com.spring.ecommerce.model.Payment;

public class PaymentMapper {

    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .amonut(request.amount())
                .paymentMethod(request.paymentMethod())
                .orderId(request.orderId())
                .build();
    }
}
