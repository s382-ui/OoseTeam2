package com.oose.labsafety.common;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T extends Identifiable> {

    void save(T item);

    void saveAll(Collection<T> items);

    List<T> findAll();

    Optional<T> findById(String id);

    boolean deleteById(String id);

    void clear();

    int size();
}
