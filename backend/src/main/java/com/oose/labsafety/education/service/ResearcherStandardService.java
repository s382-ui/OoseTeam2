package com.oose.labsafety.education.service;

import com.oose.labsafety.education.domain.CompletionStandard;
import com.oose.labsafety.education.domain.ResearcherCategory;
import com.oose.labsafety.education.infrastructure.EducationRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResearcherStandardService {
    private final EducationRepository repository;

    public ResearcherStandardService(EducationRepository repository) {
        this.repository = repository;
    }

    public void register(@Valid ResearcherStandardRequest request) {
        if (request.effectiveFrom().compareTo(request.effectiveTo()) > 0) {
            throw new IllegalArgumentException("기준 적용 시작일은 종료일보다 늦을 수 없습니다.");
        }
        if (repository.researcherCategories().findById(request.researcherCategoryId()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 분류 ID입니다: " + request.researcherCategoryId());
        }
        if (repository.completionStandards().findById(request.completionStandardId()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 이수기준 ID입니다: " + request.completionStandardId());
        }

        repository.researcherCategories().save(new ResearcherCategory(
                request.researcherCategoryId(),
                request.categoryName(),
                request.description(),
                request.active()));
        repository.completionStandards().save(new CompletionStandard(
                request.completionStandardId(),
                request.researcherCategoryId(),
                request.requiredHours(),
                request.effectiveFrom(),
                request.effectiveTo()));
    }

    public List<ResearcherStandardView> findAll() {
        List<ResearcherCategory> categories = repository.researcherCategories().findAll();
        return repository.completionStandards().findAll().stream()
                .map(standard -> {
                    ResearcherCategory category = categories.stream()
                            .filter(item -> item.researcherCategoryId().equals(standard.researcherCategoryId()))
                            .findFirst()
                            .orElse(null);
                    return new ResearcherStandardView(
                            standard.completionStandardId(),
                            standard.researcherCategoryId(),
                            category == null ? "미등록 분류" : category.categoryName(),
                            standard.requiredHours(),
                            standard.effectiveFrom(),
                            standard.effectiveTo(),
                            category != null && category.active());
                })
                .toList();
    }

    public record ResearcherStandardRequest(
            @NotBlank(message = "분류 ID는 필수입니다.") String researcherCategoryId,
            @NotBlank(message = "분류명은 필수입니다.") String categoryName,
            String description,
            boolean active,
            @NotBlank(message = "이수기준 ID는 필수입니다.") String completionStandardId,
            @Min(value = 1, message = "필수 이수시간은 1 이상이어야 합니다.") int requiredHours,
            @NotBlank(message = "적용 시작일은 필수입니다.") String effectiveFrom,
            @NotBlank(message = "적용 종료일은 필수입니다.") String effectiveTo
    ) {
    }

    public record ResearcherStandardView(
            String completionStandardId,
            String researcherCategoryId,
            String categoryName,
            int requiredHours,
            String effectiveFrom,
            String effectiveTo,
            boolean active
    ) {
    }
}
