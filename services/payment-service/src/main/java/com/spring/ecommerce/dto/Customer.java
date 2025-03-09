package com.spring.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "first name is required")
        String firstName,
        @NotNull(message = "first name is required")
        String lastName,
        @NotNull(message = "emial is required")
        @Email(message = "customer email is not correctly formated")
        String email
) {
}
