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

    @Override
    public Chemical register(Chemical chemical) {
        if (chemical.casNumber() != null && !chemical.casNumber().isBlank()) {
            boolean duplicatedCas = repository.findAll().stream()
                    .anyMatch(item -> chemical.casNumber().equalsIgnoreCase(item.casNumber()));
            if (duplicatedCas) {
                throw new IllegalArgumentException("이미 등록된 CAS 번호입니다: " + chemical.casNumber());
            }
        }
        return super.register(chemical);
    }
}
