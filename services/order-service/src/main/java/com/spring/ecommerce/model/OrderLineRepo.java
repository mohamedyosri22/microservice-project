package com.spring.ecommerce.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepo extends JpaRepository<OrderLine,Integer> {
}
