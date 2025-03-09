package com.spring.ecommerce.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.beans.BeanProperty;

@Configuration
public class KafkaPaymentTopicConfig {
    @Bean
    public NewTopic PaymentTopic(){
        return TopicBuilder
                .name("payment-topic")
                .build();
    }
}
