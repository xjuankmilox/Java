package test.java.com.example.demo.Products.infrastructure.controller;

import com.example.demo.Inventory.application.command.UpdateInventoryCommand;
import com.example.demo.Inventory.application.handler.CheckStockHandler;
import com.example.demo.Inventory.application.handler.UpdateStockHandler;
import com.example.demo.Inventory.application.query.CheckStockQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryControllerTest {

    @Mock
    private UpdateStockHandler updateStockHandler;

    @Mock
    private CheckStockHandler checkStockHandler;

    @InjectMocks
    private InventoryController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnQuantity() {
        Long productId = 1L;
        int quantity = 10;

        when(checkStockHandler.handle(new CheckStockQuery(productId))).thenReturn(quantity);

        ResponseEntity<Integer> response = controller.getStock(productId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(quantity, response.getBody());
    }

    @Test
    void shouldUpdateStock() {
        UpdateInventoryCommand command = new UpdateInventoryCommand(1L, 15);

        ResponseEntity<Void> response = controller.updateStock(command);

        assertEquals(204, response.getStatusCodeValue());
        verify(updateStockHandler).handle(command);
    }
}

