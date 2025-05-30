package com.example.demo.Inventory.application.query;
import lombok.Getter;


@Getter
public class GetProductQuery {
    
    private final Long productId;

    public GetProductQuery(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
