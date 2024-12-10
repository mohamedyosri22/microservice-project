package com.spring.ecommerce.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Customer {
    @Id
    private String id;

    private String firstname;
    private String lastname;
    private String email;
    private Address address;
}
