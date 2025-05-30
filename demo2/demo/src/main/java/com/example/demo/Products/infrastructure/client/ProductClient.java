package com.example.demo.Products.infrastructure.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class ProductClient {

    private final RestTemplate restTemplate;

    public ProductClient() {
        this.restTemplate = new RestTemplate(); // O injecta uno configurado
    }

    public boolean existsById(Long productId) {
        try {
            String url = "http://localhost:8081/api/product/" + productId; // Ajusta el host del microservicio de productos
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}