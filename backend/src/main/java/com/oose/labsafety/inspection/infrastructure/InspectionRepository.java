package com.oose.labsafety.inspection.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oose.labsafety.common.infrastructure.JsonFileRepository;
import com.oose.labsafety.inspection.domain.InspectionCategory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class InspectionRepository extends JsonFileRepository<InspectionCategory> {
    public InspectionRepository(ObjectMapper mapper, @Value("${labsafety.data-directory:data}") String directory) {
        super(mapper, InspectionCategory.class, "data/inspection/inspectionCategories.json", "inspection/inspectionCategories.json", directory);
    }
}
