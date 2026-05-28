package com.oose.labsafety.chemical.application;

import com.oose.labsafety.chemical.domain.ChemicalItem;
import com.oose.labsafety.chemical.infrastructure.ChemicalRepository;
import com.oose.labsafety.common.AbstractCatalogService;

public final class ChemicalService extends AbstractCatalogService<ChemicalItem> {

    public ChemicalService(ChemicalRepository repository) {
        super(repository);
    }
}