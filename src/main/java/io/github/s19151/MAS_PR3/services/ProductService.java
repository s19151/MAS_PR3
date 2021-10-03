package io.github.s19151.MAS_PR3.services;

import io.github.s19151.MAS_PR3.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }
}
