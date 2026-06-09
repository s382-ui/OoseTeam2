package com.oose.labsafety.waste.service;

import com.oose.labsafety.common.AbstractCatalogService;
import com.oose.labsafety.waste.domain.WasteRecord;
import com.oose.labsafety.waste.infrastructure.WasteRepository;

public final class WasteService extends AbstractCatalogService<WasteRecord> {

    public WasteService(WasteRepository repository) {
        super(repository);
    }
}