package com.example.demo.Products.application.handler;

import com.example.demo.Products.application.query.CheckStockQuery;
import com.example.demo.Products.domain.model.Inventory;
import com.example.demo.Products.domain.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckStockHandler {
    private final InventoryRepository repository;

    public CheckStockHandler(InventoryRepository repository) {
        this.repository = repository;
    }

    public Optional<Inventory> handle(CheckStockQuery query) {
        return repository.findByProductId(query.getProductId());
    }
}
