package test.java.com.example.demo.Products.application.handler;

import com.example.demo.Inventory.application.query.CheckStockQuery;
import com.example.demo.Inventory.domain.model.Inventory;
import com.example.demo.Inventory.domain.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckStockHandlerTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private CheckStockHandler checkStockHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnQuantityIfInventoryExists() {
        Long productId = 1L;
        int quantity = 25;
        Inventory inventory = new Inventory(1L, productId, quantity);

        when(inventoryRepository.findByProductId(productId)).thenReturn(Optional.of(inventory));

        int result = checkStockHandler.handle(new CheckStockQuery(productId));

        assertEquals(quantity, result);
        verify(inventoryRepository).findByProductId(productId);
    }

    @Test
    void shouldThrowIfInventoryNotFound() {
        Long productId = 2L;

        when(inventoryRepository.findByProductId(productId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> checkStockHandler.handle(new CheckStockQuery(productId))
        );

        assertEquals("No se encontró inventario para el producto con ID 2", exception.getMessage());
        verify(inventoryRepository).findByProductId(productId);
    }
}
