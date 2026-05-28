package com.oose.labsafety.laboratory.application;

import com.oose.labsafety.common.AbstractCatalogService;
import com.oose.labsafety.laboratory.domain.LaboratoryProfile;
import com.oose.labsafety.laboratory.infrastructure.LaboratoryRepository;

public final class LaboratoryService extends AbstractCatalogService<LaboratoryProfile> {

    public LaboratoryService(LaboratoryRepository repository) {
        super(repository);
    }
}