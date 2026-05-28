package com.oose.labsafety.education.application;

import com.oose.labsafety.common.AbstractCatalogService;
import com.oose.labsafety.education.domain.EducationRecord;
import com.oose.labsafety.education.infrastructure.EducationRepository;

public final class EducationService extends AbstractCatalogService<EducationRecord> {

    public EducationService(EducationRepository repository) {
        super(repository);
    }
}