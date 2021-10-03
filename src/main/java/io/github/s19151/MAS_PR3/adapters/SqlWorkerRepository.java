package io.github.s19151.MAS_PR3.adapters;

import io.github.s19151.MAS_PR3.models.Worker;
import io.github.s19151.MAS_PR3.repositories.WorkerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlWorkerRepository extends WorkerRepository, JpaRepository<Worker, Integer> {
}
