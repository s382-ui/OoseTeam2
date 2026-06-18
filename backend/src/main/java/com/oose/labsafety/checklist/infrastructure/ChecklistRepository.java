package com.oose.labsafety.checklist.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oose.labsafety.checklist.domain.ChecklistItem;
import com.oose.labsafety.common.infrastructure.JsonFileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class ChecklistRepository extends JsonFileRepository<ChecklistItem> {
    public ChecklistRepository(ObjectMapper mapper, @Value("${labsafety.data-directory:data}") String directory) {
        super(mapper, ChecklistItem.class, "data/inspection/checklistItems.json", "inspection/checklistItems.json", directory);
    }
}
