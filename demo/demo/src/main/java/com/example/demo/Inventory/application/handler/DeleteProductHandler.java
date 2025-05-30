package com.example.demo.Inventory.application.handler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Inventory.application.command.DeleteProductCommand;
import com.example.demo.Inventory.domain.repository.ProductRepository;

/**
 * Manejador de comando para eliminar un producto del sistema.
 */
@Component
public class DeleteProductHandler {

    private final ProductRepository repository;

    public DeleteProductHandler(ProductRepository repository) {
        this.repository = repository;
    }

    /**
     * Maneja el comando DeleteProductCommand buscando el producto por su ID
     * y eliminándolo si existe.
     *
     * @param command el comando que contiene el ID del producto a eliminar
     */
    @Transactional
    public void handle(DeleteProductCommand command) {
        long Id = command.getId();

        // Verifica si el producto existe antes de intentar eliminarlo
        repository.findById(Id).ifPresentOrElse(
            productId -> repository.deleteById(command.getId()),
            () -> { throw new IllegalArgumentException("El producto con ID " + Id + " no existe."); }
        );
    }
}