package com.oose.labsafety.common.service;

import com.oose.labsafety.common.domain.Identifiable;
import com.oose.labsafety.common.infrastructure.JsonFileRepository;

import java.util.List;

public class CrudService<T extends Identifiable> {
    protected final JsonFileRepository<T> repository;

    protected CrudService(JsonFileRepository<T> repository) {
        this.repository = repository;
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T register(T entity) {
        if (repository.findById(entity.id()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 ID입니다: " + entity.id());
        }
        return repository.save(entity);
    }

    public void delete(String id) {
        if (repository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("삭제할 데이터를 찾을 수 없습니다: " + id);
        }
        repository.deleteById(id);
    }
}
