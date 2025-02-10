package com.spring.ecommerce.exception;


public class ProductPurchaseException extends RuntimeException{
    public ProductPurchaseException(String msg){
        super(msg);
    }
}
