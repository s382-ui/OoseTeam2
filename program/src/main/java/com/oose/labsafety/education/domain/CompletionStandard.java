package com.oose.labsafety.education.domain;

import java.time.LocalDate;

public record CompletionStandard(
        String standardId,
        String researcherCategoryId,
        int requiredHours,
        LocalDate startDate,
        LocalDate endDate
) {
    public String summary() {
        return "이수기준ID=%s, 분류ID=%s, 필수시간=%d, 적용기간=%s~%s".formatted(
                standardId, researcherCategoryId, requiredHours, startDate, endDate);
    }
}
