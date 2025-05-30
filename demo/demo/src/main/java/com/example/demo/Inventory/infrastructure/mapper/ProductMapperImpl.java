package com.example.demo.Inventory.infrastructure.mapper;

import org.springframework.stereotype.Component;
import com.example.demo.Inventory.application.command.CreateProductCommand;
import com.example.demo.Inventory.application.command.UpdateProductCommand;
import com.example.demo.Inventory.domain.model.Product;
import com.example.demo.Inventory.infrastructure.persistence.ProductEntity;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(CreateProductCommand command) {
        return new Product(null, command.getName(), command.getPrice());
    }

    @Override
    public Product toEntity(UpdateProductCommand command) {
        return new Product(command.getId(), command.getName(), command.getPrice());
    }

    @Override
    public ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        return entity;
    }

    @Override
    public Product toDomain(ProductEntity entity) {
        return new Product(entity.getId(), entity.getName(), entity.getPrice());
    }
}
