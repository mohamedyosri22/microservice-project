package com.spring.ecommerce.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
