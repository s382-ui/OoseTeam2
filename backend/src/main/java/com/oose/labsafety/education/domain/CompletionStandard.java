package com.oose.labsafety.education.domain;

import com.oose.labsafety.common.domain.Identifiable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CompletionStandard(
        @NotBlank(message = "이수기준 ID는 필수입니다.") String completionStandardId,
        @NotBlank(message = "연구활동종사자 분류 ID는 필수입니다.") String researcherCategoryId,
        @Min(value = 1, message = "필수 이수시간은 1 이상이어야 합니다.") int requiredHours,
        @NotBlank(message = "기준 적용 시작일은 필수입니다.") String effectiveFrom,
        @NotBlank(message = "기준 적용 종료일은 필수입니다.") String effectiveTo
) implements Identifiable {
    @Override
    public String id() {
        return completionStandardId;
    }
}
