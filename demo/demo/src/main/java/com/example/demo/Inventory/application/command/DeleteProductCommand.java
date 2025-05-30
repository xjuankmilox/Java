package com.example.demo.Inventory.application.command;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
//Elimina un producto existente
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteProductCommand {
    @NotNull(message = "El ID del producto no puede ser nulo")
    @Min(value = 1, message = "El ID del producto debe ser mayor o igual a 1")
    private Long Id;

}