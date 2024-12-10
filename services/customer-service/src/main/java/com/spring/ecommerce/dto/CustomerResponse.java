package com.spring.ecommerce.dto;

import com.spring.ecommerce.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

public record CustomerResponse(
        String id,
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @NotNull
        @Email
        String email,
        Address address
) {
}
