package com.spring.ecommerce.dto;

import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest request) {
        if (request==null){
            return null;
        }
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                //because category is a model too
                .category(Category.builder()
                        .id(request.categoryId())
                        .build())

                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        if (product ==null){
            return null;
        }
        return new ProductResponse(
                product.getId(), product.getName(), product.getDescription(),
                product.getAvailableQuantity(), product.getPrice(),
                product.getCategory().getId(),product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {

        return new ProductPurchaseResponse(
                product.getId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getAvailableQuantity()
        );
    }
}
