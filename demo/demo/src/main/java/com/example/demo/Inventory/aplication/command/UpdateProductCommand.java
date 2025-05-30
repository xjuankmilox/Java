package com.example.demo.Inventory.aplication.command;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//actualizar un producto existente.
 @Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductCommand {
    @NotNull(message = "El ID del producto no puede ser nulo")
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotNull(message = "El precio no puede ser nulo")
    @Min(value = 0, message = "El precio debe ser mayor o igual a 0")
    private Double precio;
}
