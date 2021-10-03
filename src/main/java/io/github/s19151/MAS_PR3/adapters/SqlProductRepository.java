package io.github.s19151.MAS_PR3.adapters;

import io.github.s19151.MAS_PR3.models.Product;
import io.github.s19151.MAS_PR3.repositories.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlProductRepository extends ProductRepository, JpaRepository<Product, Integer> {
}
