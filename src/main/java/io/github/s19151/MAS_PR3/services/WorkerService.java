package io.github.s19151.MAS_PR3.services;

import io.github.s19151.MAS_PR3.repositories.WorkerRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {
    private WorkerRepository repository;

    public WorkerService(WorkerRepository repository) {
        this.repository = repository;
    }
}
