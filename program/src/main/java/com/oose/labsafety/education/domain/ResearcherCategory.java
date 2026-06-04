package com.oose.labsafety.education.domain;

public record ResearcherCategory(
        String researcherCategoryId,
        String categoryName,
        String categoryDescription,
        boolean active
) {
    public String summary() {
        return "분류ID=%s, 분류명=%s, 설명=%s, 사용=%s".formatted(
                researcherCategoryId, categoryName, categoryDescription, active ? "Y" : "N");
    }
}
