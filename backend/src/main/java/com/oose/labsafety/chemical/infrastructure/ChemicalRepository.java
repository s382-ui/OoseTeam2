package com.oose.labsafety.chemical.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oose.labsafety.chemical.domain.Chemical;
import com.oose.labsafety.common.infrastructure.JsonFileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class ChemicalRepository extends JsonFileRepository<Chemical> {
    public ChemicalRepository(ObjectMapper mapper, @Value("${labsafety.data-directory:data}") String directory) {
        super(mapper, Chemical.class, "data/chemical/chemicals.json", "chemical/chemicals.json", directory);
    }
}
