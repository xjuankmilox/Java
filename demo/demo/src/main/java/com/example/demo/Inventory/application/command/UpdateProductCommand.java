package com.example.demo.Inventory.application.command;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UpdateProductCommand {
    
    @NotNull(message = "The product ID cannot be null")
    private Long Id;

    @NotBlank(message = "The name cannot be blank")
    private String name;

    private String description;

    @NotNull(message = "The price cannot be null")
    @Min(value = 0, message = "The price must be greater than or equal to 0")
    private Double price;

    public UpdateProductCommand() {}

    public UpdateProductCommand(long Id, String name, String description, Double price) {
        this.Id = Id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
    
}
