package com.example.demo.Inventory.domain.repository;

import org.springframework.stereotype.Repository;
import com.example.demo.Inventory.domain.model.Product;

import com.example.demo.Inventory.infrastructure.mapper.ProductMapper;
import com.example.demo.Inventory.infrastructure.persistence.JpaProductRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaRepository;
    private final ProductMapper mapper;

    public ProductRepositoryImpl(JpaProductRepository jpaRepository, ProductMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Product save(Product product) {
        return mapper.toDomain(
            jpaRepository.save(mapper.toEntity(product))
        );
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
}
