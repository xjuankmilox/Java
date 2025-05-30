package com.example.demo.Inventory.application.handler;

import com.example.demo.Inventory.infrastructure.mapper.ProductMapper;

import jakarta.persistence.Id;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.demo.Inventory.domain.model.Product;
import com.example.demo.Inventory.domain.repository.ProductRepository;
import com.example.demo.Inventory.application.query.GetProductQuery;

/**
 * Manejador de consulta para obtener un producto por su ID.
 */
@Component
public class GetProductHandler {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public GetProductHandler(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Maneja la consulta GetProductQuery y devuelve el producto correspondiente.
     *
     * @param query la consulta que contiene el ID del producto a obtener
     * @return el producto encontrado
     */
    /*public Product handle(GetProductQuery query) {
        long productId = query.getProductId();

        // Busca y retorna el producto por su ID
        return repository.findById(productId).orElseThrow(
            () -> new IllegalArgumentException("El producto con ID " + productId + " no existe.")
        );
    }
    */
    public Product handle(GetProductQuery query) {
    Optional<Product> productOpt = repository.findById(query.getProductId());
    System.out.println("Buscando producto con ID " + query.getProductId());
    if (productOpt.isEmpty()) {
        System.out.println("Producto no encontrado!");
        throw new IllegalArgumentException("El producto con ID " + query.getProductId() + " no existe.");
    }
    System.out.println("Producto encontrado: " + productOpt.get());
    return productOpt.get();
}

} 
