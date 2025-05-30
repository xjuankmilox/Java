package com.example.demo.Products.application.query;

public class CheckStockQuery {
    private final Long productId;

    public CheckStockQuery(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
