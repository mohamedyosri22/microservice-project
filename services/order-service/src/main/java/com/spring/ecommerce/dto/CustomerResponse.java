package com.spring.ecommerce.dto;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String Email
) {
}
