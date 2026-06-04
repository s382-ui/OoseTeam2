package com.oose.labsafety.education.domain;

import java.time.LocalDate;

public record EducationOpening(
        String openingId,
        String courseId,
        String courseName,
        String educationType,
        int recognizedHours,
        LocalDate educationStartDate,
        LocalDate educationEndDate,
        String openingStatus
) {
    public String summary() {
        return "개설ID=%s, 과정ID=%s, 과정명=%s, 유형=%s, 인정시간=%d, 기간=%s~%s, 상태=%s".formatted(
                openingId, courseId, courseName, educationType, recognizedHours,
                educationStartDate, educationEndDate, openingStatus);
    }
}
