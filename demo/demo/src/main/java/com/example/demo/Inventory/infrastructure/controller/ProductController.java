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
import com.example.demo.Inventory.infrastructure.Dtos.ProductRequest;
import com.example.demo.Inventory.infrastructure.Dtos.ProductResponse;
import com.example.demo.Inventory.infrastructure.mapper.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final CreateProductHandler createHandler;
    private final UpdateProductHandler updateHandler;
    private final DeleteProductHandler deleteHandler;
    private final GetProductHandler getHandler;
    private final ListProductHandler listHandler;
    private final ProductMapper productMapper;

    public ProductController(
            CreateProductHandler createHandler,
            UpdateProductHandler updateHandler,
            DeleteProductHandler deleteHandler,
            GetProductHandler getHandler,
            ListProductHandler listHandler,
            ProductMapper productMapper
    ) {
        this.createHandler = createHandler;
        this.updateHandler = updateHandler;
        this.deleteHandler = deleteHandler;
        this.getHandler = getHandler;
        this.listHandler = listHandler;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody ProductRequest request) {
        CreateProductCommand command = new CreateProductCommand(request.getName(), request.getPrice());
        Long id = createHandler.handle(command);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ProductRequest request) {
        UpdateProductCommand command = new UpdateProductCommand(id, request.getName(), request.getPrice());
        updateHandler.handle(command);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteHandler.handle(new DeleteProductCommand(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> get(@PathVariable Long id) {
        Product product = getHandler.handle(new GetProductQuery(id));
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        ProductResponse response = productMapper.toResponse(product);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> list() {
        List<Product> products = listHandler.handle(new ListProductQuery());
        List<ProductResponse> responses = products.stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}

    
