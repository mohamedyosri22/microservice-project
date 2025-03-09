package com.spring.ecommerce.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepo extends JpaRepository<OrderLine,Integer> {
    public List<OrderLine> findAllByOrderId(Integer orderId);
}
