package io.github.s19151.MAS_PR3.repositories;

import java.util.List;
import java.util.Optional;

public interface SimpleRepository<T, ID> {
    List<T> findAll();

    Optional<T> findById(ID id);

    T save(T entity);
}
