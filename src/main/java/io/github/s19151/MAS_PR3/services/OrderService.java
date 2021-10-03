package io.github.s19151.MAS_PR3.services;

import io.github.s19151.MAS_PR3.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }
}
