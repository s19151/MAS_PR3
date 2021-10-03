package io.github.s19151.MAS_PR3.adapters;

import io.github.s19151.MAS_PR3.models.Client;
import io.github.s19151.MAS_PR3.repositories.ClientRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlClientRepository extends ClientRepository, JpaRepository<Client, Integer> {
}
