package com.oose.labsafety.common;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCatalogService<T extends Identifiable> {

    private final CrudRepository<T> repository;

    protected AbstractCatalogService(CrudRepository<T> repository) {
        this.repository = repository;
    }

    public void register(T item) {
        repository.save(item);
    }

    public boolean existsById(String id) {
        return repository.findById(id).isPresent();
    }

    public boolean update(T item) {
        if (!existsById(item.id())) {
            return false;
        }
        repository.save(item);
        return true;
    }

    public void seed(Collection<T> items) {
        repository.saveAll(items);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public Optional<T> findById(String id) {
        return repository.findById(id);
    }

    public boolean deleteById(String id) {
        return repository.deleteById(id);
    }

    public void clear() {
        repository.clear();
    }

    public int size() {
        return repository.size();
    }
}
