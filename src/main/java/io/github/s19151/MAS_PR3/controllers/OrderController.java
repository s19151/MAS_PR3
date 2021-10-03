package io.github.s19151.MAS_PR3.controllers;

import io.github.s19151.MAS_PR3.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    private static Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderRepository repository;

    public OrderController(final OrderRepository repository) {
        this.repository = repository;
    }
}
