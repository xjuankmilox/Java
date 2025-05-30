package com.example.demo.Products.application.handler;

import com.example.demo.Products.application.command.UpdateInventoryCommand;
import com.example.demo.Products.domain.model.Inventory;
import com.example.demo.Products.domain.repository.InventoryRepository;
import com.example.demo.Products.infrastructure.client.ProductClient;
import org.springframework.stereotype.Service;

@Service
public class UpdateStockHandler {

    private final InventoryRepository inventoryRepository;
    private final ProductClient productClient;

    public UpdateStockHandler(InventoryRepository inventoryRepository, ProductClient productClient) {
        this.inventoryRepository = inventoryRepository;
        this.productClient = productClient;
    }

    public void handle(UpdateInventoryCommand command) {
        Long productId = command.getProductId();

        // Validación con microservicio de productos
        if (!productClient.existsById(productId)) {
            throw new IllegalArgumentException("Producto con ID " + productId + " no existe.");
        }

        Inventory inventory = inventoryRepository.findByProductId(productId)
            .orElse(new Inventory(null, productId, 0));

        inventory.setQuantity(command.getQuantity());

        inventoryRepository.save(inventory);

        // Emitir evento simple (mensaje en consola)
        System.out.println("Inventario actualizado para producto ID " + productId +
                           ": nueva cantidad = " + command.getQuantity());
    }
}
