package com.oose.labsafety.education.application;

import com.oose.labsafety.education.domain.CompletionStandard;
import com.oose.labsafety.education.domain.ResearcherCategory;
import com.oose.labsafety.education.infrastructure.EducationRepository;

import java.util.List;

public final class ResearcherStandardControl {

    private final EducationRepository repository;

    public ResearcherStandardControl(EducationRepository repository) {
        this.repository = repository;
    }

    public boolean registerResearcherCategory(ResearcherCategory category) {
        if (category.researcherCategoryId().isBlank() || category.categoryName().isBlank()) {
            return false;
        }
        repository.saveResearcherCategory(category);
        return true;
    }

    public boolean registerCompletionStandard(CompletionStandard standard) {
        if (!validateStandardPeriod(standard)) {
            return false;
        }
        repository.saveCompletionStandard(standard);
        return true;
    }

    public List<ResearcherCategory> searchResearcherCategory(String keyword) {
        return repository.findResearcherCategories(keyword);
    }

    public List<CompletionStandard> searchCompletionStandard(String keyword) {
        return repository.findCompletionStandards(keyword);
    }

    public boolean checkCategoryDuplicate(String categoryName) {
        return repository.existsResearcherCategoryByName(categoryName);
    }

    public boolean validateStandardPeriod(CompletionStandard standard) {
        return !standard.endDate().isBefore(standard.startDate());
    }
}
