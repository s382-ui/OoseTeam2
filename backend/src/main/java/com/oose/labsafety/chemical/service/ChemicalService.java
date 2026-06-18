package com.oose.labsafety.chemical.service;

import com.oose.labsafety.chemical.domain.Chemical;
import com.oose.labsafety.chemical.infrastructure.ChemicalRepository;
import com.oose.labsafety.common.service.CrudService;
import org.springframework.stereotype.Service;

@Service
public class ChemicalService extends CrudService<Chemical> {
    public ChemicalService(ChemicalRepository repository) {
        super(repository);
    }
}
