package com.oose.labsafety.education.domain;

public record EducationCourse(
        String courseId,
        String courseName,
        String educationType,
        int recognizedHours
) {
    public String summary() {
        return "교육과정ID=%s, 과정명=%s, 유형=%s, 인정시간=%d".formatted(
                courseId, courseName, educationType, recognizedHours);
    }
}
