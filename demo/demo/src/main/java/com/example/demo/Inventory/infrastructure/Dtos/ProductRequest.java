package com.example.demo.Inventory.infrastructure.Dtos;
import java.math.BigDecimal;

public class ProductRequest {
    private String name;
    private double price;

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
