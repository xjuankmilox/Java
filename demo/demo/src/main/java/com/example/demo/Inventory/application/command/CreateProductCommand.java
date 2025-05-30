package com.example.demo.Inventory.application.command;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Comando para crear un producto.
 
@Getter
@Setter
@NoArgsConstructor // Genera constructor vacío
public class CreateProductCommand {
    @NotBlank(message = "El Id no puede estar vacío")
    @NotNull(message = "El Id no puede ser nulo")
    private long Id;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    @NotNull(message = "El precio no puede ser nulo")
    @Min(value = 0, message = "El precio debe ser mayor o igual a 0")
    private Double price;

    public CreateProductCommand(String name, Double price) {
        this.name = name;
        this.price = price;
    }
    public CreateProductCommand(Long Id,String name, Double price) {
        this.Id=Id;
        this.name = name;
        this.price = price;
    }
}
