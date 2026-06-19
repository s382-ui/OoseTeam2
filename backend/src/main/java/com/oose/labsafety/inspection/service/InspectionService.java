package com.oose.labsafety.inspection.service;

import com.oose.labsafety.common.service.CrudService;
import com.oose.labsafety.inspection.domain.InspectionCategory;
import com.oose.labsafety.inspection.infrastructure.InspectionRepository;
import org.springframework.stereotype.Service;

@Service
public class InspectionService extends CrudService<InspectionCategory> {
    public InspectionService(InspectionRepository repository) {
        super(repository);
    }
}
