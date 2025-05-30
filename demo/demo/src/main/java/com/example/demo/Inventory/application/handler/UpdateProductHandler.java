package com.example.demo.Inventory.application.handler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Inventory.application.command.UpdateProductCommand;
import com.example.demo.Inventory.domain.model.Product;
import com.example.demo.Inventory.domain.repository.ProductRepository;
import com.example.demo.Inventory.infrastructure.mapper.ProductMapper;

/**
 * Manejador de comando para actualizar un producto existente en el sistema.
 */
@Component
public class UpdateProductHandler {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public UpdateProductHandler(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Maneja el comando UpdateProductCommand buscando el producto por su ID,
     * actualizando sus valores y persistiendo los cambios.
     *
     * @param command el comando que contiene los nuevos datos del producto
     */
    @Transactional
    public void handle(UpdateProductCommand command) {
        long productId = command.getId();

        // Busca el producto existente
        Product existingProduct = repository.findById(productId).orElseThrow(
            () -> new IllegalArgumentException("El producto con ID " + productId + " no existe.")
        );

        // Actualiza los datos del producto a partir del comando
        Product updatedProduct = mapper.toEntity(command);
        updatedProduct.setId(existingProduct.getId()); // Asegura que se mantenga el mismo ID

        // Persiste los cambios en el repositorio
        repository.save(updatedProduct);
    }
} 
