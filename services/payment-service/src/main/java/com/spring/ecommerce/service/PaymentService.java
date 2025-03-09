package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.PaymentMapper;
import com.spring.ecommerce.dto.PaymentRequest;
import com.spring.ecommerce.notification.NotificationProducer;
import com.spring.ecommerce.notification.PaymentNotificationRequest;
import com.spring.ecommerce.repository.PaymentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepo paymentRepo;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;
    public Integer createPayment(PaymentRequest request) {
        var payment = paymentRepo.save(mapper.toPayment(request));
        notificationProducer.SendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstName(),
                        request.customer().lastName(),
                        request.customer().email()
                )
        );
        return payment.getId();
    }
}
