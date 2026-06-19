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

    @Override
    public ChecklistItem register(ChecklistItem item) {
        boolean duplicated = repository.findAll().stream()
                .anyMatch(existing -> existing.itemName().equalsIgnoreCase(item.itemName())
                        && existing.categoryName().equalsIgnoreCase(item.categoryName())
                        && existing.inspectionType().equalsIgnoreCase(item.inspectionType())
                        && existing.useYn());
        if (duplicated) {
            throw new IllegalArgumentException("같은 항목명, 분야, 점검유형의 체크리스트가 이미 있습니다.");
        }
        return super.register(item);
    }
}
