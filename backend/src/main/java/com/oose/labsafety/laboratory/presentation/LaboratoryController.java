package com.oose.labsafety.laboratory.presentation;

import com.oose.labsafety.common.presentation.CrudController;
import com.oose.labsafety.laboratory.domain.Laboratory;
import com.oose.labsafety.laboratory.service.LaboratoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/laboratories")
public class LaboratoryController extends CrudController<Laboratory> {
    public LaboratoryController(LaboratoryService service) {
        super(service);
    }
}
