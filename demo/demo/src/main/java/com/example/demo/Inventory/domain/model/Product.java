package com.example.demo.Inventory.domain.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "product")
public class Product {

    private Long Id;
    private String name;
    private Double price;

    // Constructor con validaciones
    public Product(Long Id, String name, Double price) {
        this.Id = Id;
        setName(name);
        setPrice(price);
    }
    public Product(Long Id, Double price,String name) {
        this.Id = Id;
        setName(name);
        setPrice(price);
    }
    public Long getId() { return Id; }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        this.name = name;
    }
    public Double getPrice() { return price; }
    public void setPrice(Double price) {
        if (price == null || price <= 0) {
            throw new IllegalArgumentException("El precio debe ser un valor positivo.");
        }
        this.price = price;
    }
}
