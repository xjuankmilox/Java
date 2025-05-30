package com.example.demo.Products.domain.repository;

import com.example.demo.Products.domain.model.Inventory;
import java.util.Optional;

public interface InventoryRepository {
    Optional<Inventory> findByProductId(Long productId);
    void updateStock(Long productId, int quantity);
}

