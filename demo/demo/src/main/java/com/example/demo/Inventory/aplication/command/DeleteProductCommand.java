package com.example.demo.Inventory.aplication.command;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//Elimina un producto existente
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteProductCommand {
    @NotNull(message = "El ID del producto no puede ser nulo")
    @Min(value = 1, message = "El ID del producto debe ser mayor o igual a 1")
    private Long productoId;
}