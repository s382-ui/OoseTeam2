package com.oose.labsafety.common.presentation;

import com.oose.labsafety.common.domain.Identifiable;
import com.oose.labsafety.common.service.CrudService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public abstract class CrudController<T extends Identifiable> {
    private final CrudService<T> service;

    protected CrudController(CrudService<T> service) {
        this.service = service;
    }

    @GetMapping
    public List<T> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public T register(@Valid @RequestBody T entity) {
        return service.register(entity);
    }
}
