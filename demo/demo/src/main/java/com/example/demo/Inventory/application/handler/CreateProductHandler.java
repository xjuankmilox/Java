package com.example.demo.Inventory.application.handler;
import com.example.demo.Inventory.application.command.CreateProductCommand;
import com.example.demo.Inventory.application.handler.CreateProductHandler;
import com.example.demo.Inventory.domain.model.Product;
import com.example.demo.Inventory.domain.repository.ProductRepository;
import com.example.demo.Inventory.infrastructure.mapper.ProductMapper;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/*Gestiona CreateProductCommand asignándolo a una entidad de dominio,
* lo conserva y devuelve el identificador generado.
*
* @param command: el comando que contiene los datos de creación del producto.
* @return: el ID del producto recién creado.
*/
@Component
public class CreateProductHandler {
    private final ProductRepository repository;


    public CreateProductHandler(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        
    }
    @Transactional
public long handle(CreateProductCommand command) {
    // Crear modelo de dominio con validaciones
    Product product = new Product(
        command.getId(),
        command.getName(),
        command.getPrice()
    );

    // Guardar usando el repositorio de dominio
    Product saved = repository.save(product);

    return saved.getId();
}
}
