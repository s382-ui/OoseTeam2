package com.oose.labsafety.education.domain;

import com.oose.labsafety.common.Displayable;
import com.oose.labsafety.common.Identifiable;

import java.time.LocalDate;

public record EducationCompletionResult(
        String completionResultId,
        String researcherId,
        String openingId,
        String learningResultId,
        String logId,
        LocalDate completionDate,
        int recognizedHours,
        String completionStatus
) implements Identifiable, Displayable {

    @Override
    public String id() {
        return completionResultId;
    }

    @Override
    public String summary() {
        return "이수결과ID=%s, 연구활동종사자ID=%s, 개설ID=%s, 학습결과ID=%s, 일지ID=%s, 이수일=%s, 인정시간=%d, 상태=%s".formatted(
                completionResultId, researcherId, openingId, learningResultId, logId, completionDate, recognizedHours, completionStatus);
    }
}
