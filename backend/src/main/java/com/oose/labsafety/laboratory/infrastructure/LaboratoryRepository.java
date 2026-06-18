package com.oose.labsafety.laboratory.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oose.labsafety.common.infrastructure.JsonFileRepository;
import com.oose.labsafety.laboratory.domain.Laboratory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class LaboratoryRepository extends JsonFileRepository<Laboratory> {
    public LaboratoryRepository(ObjectMapper mapper, @Value("${labsafety.data-directory:data}") String directory) {
        super(mapper, Laboratory.class, "data/laboratory/laboratories.json", "laboratory/laboratories.json", directory);
    }
}
