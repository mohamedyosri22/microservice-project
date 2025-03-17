package com.spring.ecommerce.model;

import com.spring.ecommerce.kafka.PaymentConfirmation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepo extends MongoRepository<Notification,String> {

}
