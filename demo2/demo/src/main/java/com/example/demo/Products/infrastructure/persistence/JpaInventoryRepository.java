package com.example.demo.Products.infrastructure.persistence;



import com.example.demo.Products.domain.model.Inventory;
import com.example.demo.Products.domain.repository.InventoryRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaInventoryRepository implements InventoryRepository {

    private final SpringDataInventoryRepository springDataRepository;

    public JpaInventoryRepository(SpringDataInventoryRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Optional<Inventory> findByProductoId(Long productoId) {
        return springDataRepository.findByProductoId(productoId)
                .map(this::toDomain);
    }

    @Override
    public Inventory save(Inventory Inventory) {
        InventoryEntity entity = toEntity(Inventory);
        return toDomain(springDataRepository.save(entity));
    }

    @Override
    public void deleteByProductoId(Long productoId) {
        springDataRepository.deleteByProductoId(productoId);
    }

    // Mapper: Entity → Domain
    private Inventory toDomain(InventoryEntity entity) {
        return new Inventory(entity.getProductoId(), entity.getCantidad());
    }

    // Mapper: Domain → Entity
    private InventoryEntity toEntity(Inventory Inventory) {
        InventoryEntity entity = new InventoryEntity();
        entity.setProductoId(Inventory.getProductoId());
        entity.setCantidad(Inventory.getCantidad());
        return entity;
    }
}
