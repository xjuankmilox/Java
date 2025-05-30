package com.example.demo.Inventory.application.Services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Inventory.domain.model.Product;
import com.example.demo.Inventory.domain.repository.ProductRepositoryImpl;

@Service
public class ProductService {
    @Autowired//injeccion de dependencia
    private ProductRepositoryImpl repositorio;

    public List<Product> Listar(){
        return repositorio.findAll();
    }
    public Product Insertar(Product Product){
        return repositorio.save(Product);
    }
    public void Borrar(long Id){
        repositorio.deleteById(Id);
    }
    public Product Actualizar(Product Product){
        return repositorio.save(Product);
    }
}

