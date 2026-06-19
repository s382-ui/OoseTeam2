package com.oose.labsafety.waste.service;

import com.oose.labsafety.common.service.CrudService;
import com.oose.labsafety.waste.domain.WasteCategory;
import com.oose.labsafety.waste.infrastructure.WasteRepository;
import org.springframework.stereotype.Service;

@Service
public class WasteService extends CrudService<WasteCategory> {
    public WasteService(WasteRepository repository) {
        super(repository);
    }
}
