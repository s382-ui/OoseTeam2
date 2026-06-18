package com.oose.labsafety.education.domain;

import com.oose.labsafety.common.domain.Identifiable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record EducationCompletionResult(
        @NotBlank(message = "교육이수결과 ID는 필수입니다.") String completionResultId,
        @NotBlank(message = "연구활동종사자 ID는 필수입니다.") String researcherId,
        @NotBlank(message = "교육개설정보 ID는 필수입니다.") String openingId,
        String learningResultId,
        String logId,
        @NotBlank(message = "이수일은 필수입니다.") String completionDate,
        @Min(value = 1, message = "인정시간은 1 이상이어야 합니다.") int recognizedHours,
        @NotBlank(message = "이수 상태는 필수입니다.") String completionStatus,
        boolean manualRegistration
) implements Identifiable {
    @Override
    public String id() {
        return completionResultId;
    }
}
