package com.oose.labsafety.waste.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oose.labsafety.common.infrastructure.JsonFileRepository;
import com.oose.labsafety.waste.domain.WasteCategory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class WasteRepository extends JsonFileRepository<WasteCategory> {
    public WasteRepository(ObjectMapper mapper, @Value("${labsafety.data-directory:data}") String directory) {
        super(mapper, WasteCategory.class, "data/waste/wasteCategories.json", "waste/wasteCategories.json", directory);
    }
}
