package com.oose.labsafety.common.infrastructure;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oose.labsafety.common.domain.Identifiable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JsonFileRepository<T extends Identifiable> {
    private final ObjectMapper objectMapper;
    private final Class<T> entityType;
    private final String seedResource;
    private final Path dataFile;

    public JsonFileRepository(
            ObjectMapper objectMapper,
            Class<T> entityType,
            String seedResource,
            String fileName,
            @Value("${labsafety.data-directory:data}") String dataDirectory) {
        this.objectMapper = objectMapper;
        this.entityType = entityType;
        this.seedResource = seedResource;
        this.dataFile = Path.of(dataDirectory, fileName);
    }

    public synchronized List<T> findAll() {
        initialize();
        try {
            JavaType listType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, entityType);
            return new ArrayList<>(objectMapper.readValue(dataFile.toFile(), listType));
        } catch (IOException exception) {
            throw new IllegalStateException("JSON 데이터를 읽을 수 없습니다: " + dataFile, exception);
        }
    }

    public synchronized Optional<T> findById(String id) {
        return findAll().stream().filter(entity -> entity.id().equals(id)).findFirst();
    }

    public synchronized T save(T entity) {
        List<T> entities = findAll();
        entities.addFirst(entity);
        write(entities);
        return entity;
    }

    public synchronized List<T> saveAll(List<T> newEntities) {
        List<T> entities = findAll();
        entities.addAll(0, newEntities);
        write(entities);
        return new ArrayList<>(newEntities);
    }

    public synchronized void deleteById(String id) {
        List<T> entities = findAll();
        entities.removeIf(entity -> entity.id().equals(id));
        write(entities);
    }

    private void initialize() {
        if (Files.exists(dataFile)) {
            return;
        }

        try {
            Files.createDirectories(dataFile.getParent());
            ClassPathResource resource = new ClassPathResource(seedResource);
            try (InputStream inputStream = resource.getInputStream()) {
                Files.copy(inputStream, dataFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException exception) {
            throw new IllegalStateException("초기 JSON 데이터를 준비할 수 없습니다: " + seedResource, exception);
        }
    }

    private void write(List<T> entities) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(dataFile.toFile(), entities);
        } catch (IOException exception) {
            throw new IllegalStateException("JSON 데이터를 저장할 수 없습니다: " + dataFile, exception);
        }
    }
}
