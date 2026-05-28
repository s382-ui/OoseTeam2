package com.oose.labsafety.inspection.application;

import com.oose.labsafety.common.AbstractCatalogService;
import com.oose.labsafety.inspection.domain.InspectionRecord;
import com.oose.labsafety.inspection.infrastructure.InspectionRepository;

public final class InspectionService extends AbstractCatalogService<InspectionRecord> {

    public InspectionService(InspectionRepository repository) {
        super(repository);
    }
}