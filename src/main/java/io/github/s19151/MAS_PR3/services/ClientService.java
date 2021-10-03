package io.github.s19151.MAS_PR3.services;

import io.github.s19151.MAS_PR3.repositories.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }
}
