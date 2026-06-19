package com.oose.labsafety.education.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oose.labsafety.common.infrastructure.JsonFileRepository;
import com.oose.labsafety.education.domain.CompletionStandard;
import com.oose.labsafety.education.domain.EducationCompletionResult;
import com.oose.labsafety.education.domain.EducationCourse;
import com.oose.labsafety.education.domain.EducationOpening;
import com.oose.labsafety.education.domain.ResearcherCategory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class EducationRepository {
    private final JsonFileRepository<ResearcherCategory> researcherCategories;
    private final JsonFileRepository<CompletionStandard> completionStandards;
    private final JsonFileRepository<EducationCourse> educationCourses;
    private final JsonFileRepository<EducationOpening> educationOpenings;
    private final JsonFileRepository<EducationCompletionResult> completionResults;

    public EducationRepository(
            ObjectMapper mapper,
            @Value("${labsafety.data-directory:data}") String directory) {
        researcherCategories = new JsonFileRepository<>(
                mapper, ResearcherCategory.class,
                "data/education/researcherCategories.json",
                "education/researcherCategories.json", directory);
        completionStandards = new JsonFileRepository<>(
                mapper, CompletionStandard.class,
                "data/education/completionStandards.json",
                "education/completionStandards.json", directory);
        educationCourses = new JsonFileRepository<>(
                mapper, EducationCourse.class,
                "data/education/educationCourses.json",
                "education/educationCourses.json", directory);
        educationOpenings = new JsonFileRepository<>(
                mapper, EducationOpening.class,
                "data/education/educationOpenings.json",
                "education/educationOpenings.json", directory);
        completionResults = new JsonFileRepository<>(
                mapper, EducationCompletionResult.class,
                "data/education/educationCompletionResults.json",
                "education/educationCompletionResults.json", directory);
    }

    public JsonFileRepository<ResearcherCategory> researcherCategories() {
        return researcherCategories;
    }

    public JsonFileRepository<CompletionStandard> completionStandards() {
        return completionStandards;
    }

    public JsonFileRepository<EducationCourse> educationCourses() {
        return educationCourses;
    }

    public JsonFileRepository<EducationOpening> educationOpenings() {
        return educationOpenings;
    }

    public JsonFileRepository<EducationCompletionResult> completionResults() {
        return completionResults;
    }
}
