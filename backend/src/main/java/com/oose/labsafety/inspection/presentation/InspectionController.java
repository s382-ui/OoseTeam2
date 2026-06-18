package com.oose.labsafety.inspection.presentation;

import com.oose.labsafety.common.presentation.CrudController;
import com.oose.labsafety.inspection.domain.InspectionCategory;
import com.oose.labsafety.inspection.service.InspectionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inspection-categories")
public class InspectionController extends CrudController<InspectionCategory> {
    public InspectionController(InspectionService service) {
        super(service);
    }
}
