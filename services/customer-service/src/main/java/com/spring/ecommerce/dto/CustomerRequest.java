package com.spring.ecommerce.dto;

import com.spring.ecommerce.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "Customer firstname required")
        String firstname,
        @NotNull(message = "Customer lastname required")
        String lastname,
        @NotNull(message = "Customer email required")
        @Email(message = "Customer email is not a vaild email address")
        String email,
        Address address
) {
}
