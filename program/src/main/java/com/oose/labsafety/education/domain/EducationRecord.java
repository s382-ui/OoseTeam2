package com.oose.labsafety.education.domain;

import com.oose.labsafety.common.Displayable;
import com.oose.labsafety.common.Identifiable;

import java.time.LocalDate;

public record EducationRecord(
        String educationId,
        String traineeName,
        String courseName,
        String department,
        LocalDate completionDate,
        String completionStatus
) implements Identifiable, Displayable {

    @Override
    public String id() {
        return educationId;
    }

    @Override
    public String summary() {
        return "교육ID=%s, 대상자=%s, 과정=%s, 부서=%s, 이수일=%s, 상태=%s".formatted(
                educationId, traineeName, courseName, department, completionDate, completionStatus);
    }
}