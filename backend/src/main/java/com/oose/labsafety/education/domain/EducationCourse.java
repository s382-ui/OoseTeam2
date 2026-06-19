package com.oose.labsafety.education.domain;

import com.oose.labsafety.common.domain.Identifiable;

public record EducationCourse(
        String courseId,
        String courseName,
        String educationType,
        int defaultHours
) implements Identifiable {
    @Override
    public String id() {
        return courseId;
    }
}
