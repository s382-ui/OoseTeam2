package com.oose.labsafety.laboratory.service;

import com.oose.labsafety.common.service.CrudService;
import com.oose.labsafety.laboratory.domain.Laboratory;
import com.oose.labsafety.laboratory.infrastructure.LaboratoryRepository;
import org.springframework.stereotype.Service;

@Service
public class LaboratoryService extends CrudService<Laboratory> {
    public LaboratoryService(LaboratoryRepository repository) {
        super(repository);
    }
}
