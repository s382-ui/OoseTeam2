package com.oose.labsafety.common;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryCrudRepository<T extends Identifiable> implements CrudRepository<T> {

    private final Map<String, T> store = new LinkedHashMap<>();

    public void save(T item) {
        store.put(item.id(), item);
    }

    public void saveAll(Collection<T> items) {
        for (T item : items) {
            save(item);
        }
    }

    public List<T> findAll() {
        return List.copyOf(store.values());
    }

    public Optional<T> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public boolean deleteById(String id) {
        return store.remove(id) != null;
    }

    public void clear() {
        store.clear();
    }

    public int size() {
        return store.size();
    }
}
