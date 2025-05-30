package com.example.demo.Inventory.domain.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class Product {
    private Long id;
    private String nombre;
    private Double precio;
}
