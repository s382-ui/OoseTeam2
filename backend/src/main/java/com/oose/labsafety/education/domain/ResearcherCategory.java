package com.oose.labsafety.education.domain;

import com.oose.labsafety.common.domain.Identifiable;
import jakarta.validation.constraints.NotBlank;

public record ResearcherCategory(
        @NotBlank(message = "연구활동종사자 분류 ID는 필수입니다.") String researcherCategoryId,
        @NotBlank(message = "분류명은 필수입니다.") String categoryName,
        String description,
        boolean active
) implements Identifiable {
    @Override
    public String id() {
        return researcherCategoryId;
    }
}
