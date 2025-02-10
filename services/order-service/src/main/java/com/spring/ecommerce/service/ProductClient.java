package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.PurchaseRequest;
import com.spring.ecommerce.dto.PurchaseResponse;
import com.spring.ecommerce.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {
    @Value("${application.config.product-url}")
    private String ProductUrl;
    private final RestTemplate restTemplate;
    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody){
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody,headers);
        ParameterizedTypeReference<List<PurchaseResponse>> responseType
                = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<PurchaseResponse>> response = restTemplate.exchange(
                ProductUrl +"/purchase",
                HttpMethod.POST,
                requestEntity,
                responseType
        );
        if(response.getStatusCode().isError()){
            throw new BusinessException("Error occured while processing the products: "+response.getStatusCode());
        }
        return response.getBody();
    }

}
