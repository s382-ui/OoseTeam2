package com.oose.labsafety.checklist.presentation;

import com.oose.labsafety.checklist.domain.ChecklistItem;
import com.oose.labsafety.checklist.service.ChecklistService;
import com.oose.labsafety.common.presentation.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checklist-items")
public class ChecklistController extends CrudController<ChecklistItem> {
    public ChecklistController(ChecklistService service) {
        super(service);
    }
}
