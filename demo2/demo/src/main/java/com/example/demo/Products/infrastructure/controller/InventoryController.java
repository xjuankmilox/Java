package com.example.demo.Products.infrastructure.controller;

import com.example.demo.Products.application.command.UpdateInventoryCommand;
import com.example.demo.Products.application.handler.CheckStockHandler;
import com.example.demo.Products.application.handler.UpdateStockHandler;
import com.example.demo.Products.application.query.CheckStockQuery;
import com.example.demo.Products.domain.model.Inventory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final CheckStockHandler checkHandler;
    private final UpdateStockHandler updateHandler;

    public InventoryController(CheckStockHandler checkHandler, UpdateStockHandler updateHandler) {
        this.checkHandler = checkHandler;
        this.updateHandler = updateHandler;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Inventory> checkStock(@PathVariable Long productId) {
        Optional<Inventory> inventory = checkHandler.handle(new CheckStockQuery(productId));
        return inventory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateStock(@PathVariable Long productId,@RequestBody UpdateInventoryCommand command) {
        command.setProductId(productId);
        updateStockHandler.handle(command);
        return ResponseEntity.noContent().build();
    }
}