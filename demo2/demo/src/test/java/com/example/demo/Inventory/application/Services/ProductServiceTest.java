package com.example.demo.Inventory.application.Services;

import com.example.demo.Inventory.domain.repository.ProductRepositoryImpl;
import com.example.demo.Inventory.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepositoryImpl repository;

    @InjectMocks
    private ProductService service;

    @Test
    void testListar() {
        List<Product> products = List.of(
                new Product(1L, "Camiseta", 19.99),
                new Product(2L, "Pantalón", 39.99)
        );

        when(repository.findAll()).thenReturn(products);

        List<Product> result = service.Listar();

        assertEquals(2, result.size());
        assertEquals("Camiseta", result.get(0).getName());
    }

    @Test
    void testInsertar() {
        Product nuevo = new Product(null, "Zapatos", 59.99);
        Product guardado = new Product(3L, "Zapatos", 59.99);

        when(repository.save(nuevo)).thenReturn(guardado);

        Product result = service.Insertar(nuevo);

        assertNotNull(result.getId());
        assertEquals("Zapatos", result.getName());
        assertEquals(59.99, result.getPrice());
    }

    @Test
    void testBorrar() {
        long id = 1L;

        service.Borrar(id);

        verify(repository).deleteById(id);
    }

    @Test
    void testActualizar() {
        try {
            Product actualizado = new Product(2L, "Pantalón Slim", 42.50);

            when(repository.save(actualizado)).thenReturn(actualizado);

            Product result = service.Actualizar(actualizado);

            assertEquals("Pantalón Slim", result.getName());
            assertEquals(42.50, result.getPrice());

            verify(repository, times(1)).save(actualizado);

        } catch (Exception e) {
            e.printStackTrace(); // Para que imprima la excepción en consola
            fail("Exception in testActualizar: " + e.getMessage());
        }
    }

}

