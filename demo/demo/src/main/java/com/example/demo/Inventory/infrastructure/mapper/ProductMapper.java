package com.example.demo.Inventory.infrastructure.mapper;



import com.example.demo.Inventory.application.command.CreateProductCommand;
import com.example.demo.Inventory.application.command.UpdateProductCommand;
import com.example.demo.Inventory.domain.model.Product;
import com.example.demo.Inventory.infrastructure.Dtos.ProductRequest;
import com.example.demo.Inventory.infrastructure.Dtos.ProductResponse;
import com.example.demo.Inventory.infrastructure.persistence.ProductEntity;

public interface ProductMapper {
    Product toEntity(CreateProductCommand command);
    Product toEntity(UpdateProductCommand command);
    ProductEntity toEntity(Product product);
    Product toDomain(ProductEntity entity);
    Product toDomain(ProductRequest request);
    ProductResponse toResponse(Product product);

}
