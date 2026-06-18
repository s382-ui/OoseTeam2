package com.oose.labsafety.checklist.service;

import com.oose.labsafety.checklist.domain.ChecklistItem;
import com.oose.labsafety.checklist.infrastructure.ChecklistRepository;
import com.oose.labsafety.common.service.CrudService;
import org.springframework.stereotype.Service;

@Service
public class ChecklistService extends CrudService<ChecklistItem> {
    public ChecklistService(ChecklistRepository repository) {
        super(repository);
    }
}
