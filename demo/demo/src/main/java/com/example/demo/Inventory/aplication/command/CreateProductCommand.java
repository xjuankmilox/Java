package com.example.demo.Inventory.aplication.command;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Comando para crear un producto.
 
@Data // Genera getters, setters
@NoArgsConstructor // Genera constructor vacío
@AllArgsConstructor // Genera constructor con todos los campos
public class CreateProductCommand {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotNull(message = "El precio no puede ser nulo")
    @Min(value = 0, message = "El precio debe ser mayor o igual a 0")
    private Double precio;
}
