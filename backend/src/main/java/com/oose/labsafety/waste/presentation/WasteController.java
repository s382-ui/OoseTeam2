package com.oose.labsafety.waste.presentation;

import com.oose.labsafety.common.presentation.CrudController;
import com.oose.labsafety.waste.domain.WasteCategory;
import com.oose.labsafety.waste.service.WasteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/waste-categories")
public class WasteController extends CrudController<WasteCategory> {
    public WasteController(WasteService service) {
        super(service);
    }
}
