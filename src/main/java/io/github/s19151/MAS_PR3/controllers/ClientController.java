package io.github.s19151.MAS_PR3.controllers;

import io.github.s19151.MAS_PR3.models.Client;
import io.github.s19151.MAS_PR3.repositories.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientController {
    private static Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ClientRepository repository;

    public ClientController(final ClientRepository repository) {
        this.repository = repository;
    }
}
