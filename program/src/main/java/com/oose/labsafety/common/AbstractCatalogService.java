package com.oose.labsafety.common;

import java.util.Collection;
import java.util.List;

public abstract class AbstractCatalogService<T extends Identifiable> {

    private final InMemoryCrudRepository<T> repository;

    protected AbstractCatalogService(InMemoryCrudRepository<T> repository) {
        this.repository = repository;
    }

    public void register(T item) {
        repository.save(item);
    }

    public void seed(Collection<T> items) {
        repository.saveAll(items);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public void clear() {
        repository.clear();
    }

    public int size() {
        return repository.size();
    }
}