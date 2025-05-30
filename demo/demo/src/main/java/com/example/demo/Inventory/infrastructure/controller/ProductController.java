package com.example.demo.Inventory.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Inventory.application.command.CreateProductCommand;
import com.example.demo.Inventory.application.command.UpdateProductCommand;
import com.example.demo.Inventory.application.command.DeleteProductCommand;
import com.example.demo.Inventory.application.query.GetProductQuery;
import com.example.demo.Inventory.application.query.ListProductQuery;
import com.example.demo.Inventory.application.handler.*;

import com.example.demo.Inventory.domain.model.Product;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final CreateProductHandler createHandler;
    private final UpdateProductHandler updateHandler;
    private final DeleteProductHandler deleteHandler;
    private final GetProductHandler getHandler;
    private final ListProductHandler listHandler;

    public ProductController(
            CreateProductHandler createHandler,
            UpdateProductHandler updateHandler,
            DeleteProductHandler deleteHandler,
            GetProductHandler getHandler,
            ListProductHandler listHandler
    ) {
        this.createHandler = createHandler;
        this.updateHandler = updateHandler;
        this.deleteHandler = deleteHandler;
        this.getHandler = getHandler;
        this.listHandler = listHandler;
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CreateProductCommand command) {
        Long id = createHandler.handle(command);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateProductCommand command) {
        command.setId(id);
        updateHandler.handle(command);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteHandler.handle(new DeleteProductCommand(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Long id) {
    Product product = getHandler.handle(new GetProductQuery(id));
    if (product == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(product);
}

    @GetMapping
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok(listHandler.handle(new ListProductQuery()));
    }
    
}