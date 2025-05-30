package com.example.demo.Inventory.infrastructure.persistence;

import com.example.demo.Inventory.domain.model.Product;
import com.example.demo.Inventory.domain.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;
@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

}
