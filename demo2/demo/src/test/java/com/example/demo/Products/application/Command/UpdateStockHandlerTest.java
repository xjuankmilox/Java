package test.java.com.example.demo.Products.application.Command;

import com.example.demo.Inventory.application.command.UpdateInventoryCommand;
import com.example.demo.Inventory.domain.model.Inventory;
import com.example.demo.Inventory.domain.repository.InventoryRepository;
import com.example.demo.Inventory.infrastructure.client.ProductClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateStockHandlerTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private ProductClient productClient;

    @InjectMocks
    private UpdateStockHandler updateStockHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdateStockWhenProductExists() {
        // Arrange
        Long productId = 1L;
        int newQuantity = 50;
        UpdateInventoryCommand command = new UpdateInventoryCommand(productId, newQuantity);

        Inventory existingInventory = new Inventory(1L, productId, 30);

        when(productClient.existsById(productId)).thenReturn(true);
        when(inventoryRepository.findByProductId(productId)).thenReturn(Optional.of(existingInventory));

        // Act
        updateStockHandler.handle(command);

        // Assert
        assertEquals(newQuantity, existingInventory.getQuantity());
        verify(inventoryRepository).save(existingInventory);
        verify(productClient).existsById(productId);
    }

    @Test
    void shouldCreateInventoryIfNotExists() {
        // Arrange
        Long productId = 2L;
        int quantity = 20;
        UpdateInventoryCommand command = new UpdateInventoryCommand(productId, quantity);

        when(productClient.existsById(productId)).thenReturn(true);
        when(inventoryRepository.findByProductId(productId)).thenReturn(Optional.empty());

        // Act
        updateStockHandler.handle(command);

        // Assert
        ArgumentCaptor<Inventory> captor = ArgumentCaptor.forClass(Inventory.class);
        verify(inventoryRepository).save(captor.capture());

        Inventory savedInventory = captor.getValue();
        assertEquals(productId, savedInventory.getProductId());
        assertEquals(quantity, savedInventory.getQuantity());
    }

    @Test
    void shouldThrowExceptionIfProductDoesNotExist() {
        // Arrange
        Long productId = 99L;
        UpdateInventoryCommand command = new UpdateInventoryCommand(productId, 10);

        when(productClient.existsById(productId)).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> updateStockHandler.handle(command)
        );

        assertEquals("Producto con ID 99 no existe.", exception.getMessage());
        verify(inventoryRepository, never()).save(any());
    }
}
