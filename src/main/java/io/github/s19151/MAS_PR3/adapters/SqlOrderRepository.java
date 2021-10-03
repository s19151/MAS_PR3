package io.github.s19151.MAS_PR3.adapters;

import io.github.s19151.MAS_PR3.models.Order;
import io.github.s19151.MAS_PR3.repositories.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlOrderRepository extends OrderRepository, JpaRepository<Order, Integer> {
}
