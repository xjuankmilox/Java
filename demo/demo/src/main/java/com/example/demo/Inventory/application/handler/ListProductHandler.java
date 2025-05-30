package com.example.demo.Inventory.application.handler;

import org.springframework.stereotype.Component;

import com.example.demo.Inventory.domain.model.Product;
import com.example.demo.Inventory.domain.repository.ProductRepository;
import com.example.demo.Inventory.infrastructure.mapper.ProductMapper;

import lombok.NoArgsConstructor;

import com.example.demo.Inventory.application.query.ListProductQuery;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Manejador de consulta para listar todos los productos del sistema.
 */
@Component
public class ListProductHandler {

    private final ProductRepository repository;
    public ListProductHandler(ProductRepository repository) {
        this.repository = repository;
    }
    
    /**
     * Maneja la consulta ListProductsQuery y devuelve la lista de productos.
     *
     * @param query la consulta para listar los productos
     * @return lista de productos encontrados
     */
    public List<Product> handle(ListProductQuery query) {
        // Recupera y retorna todos los productos desde el repositorio
        return repository.findAll();
    }
    
} 