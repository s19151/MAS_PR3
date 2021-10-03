package io.github.s19151.MAS_PR3.controllers;

import io.github.s19151.MAS_PR3.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/worker")
public class WorkerController {
    private static Logger logger = LoggerFactory.getLogger(WorkerController.class);
    private final WorkerRepository repository;

    public WorkerController(final WorkerRepository repository) {
        this.repository = repository;
    }
}
