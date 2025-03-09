package com.spring.ecommerce.notification;

import com.spring.ecommerce.model.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
    private final KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate;
    public void SendNotification(PaymentNotificationRequest request){
        log.info("Sending notification with body <{}>",request);
        Message<PaymentNotificationRequest> message = MessageBuilder
                .withPayload(request)
                .setHeader(KafkaHeaders.TOPIC,"payment-topic")
                .build();

        kafkaTemplate.send(message);
    }
}
