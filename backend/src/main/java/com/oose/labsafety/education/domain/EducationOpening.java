package com.oose.labsafety.education.domain;

import com.oose.labsafety.common.domain.Identifiable;

public record EducationOpening(
        String openingId,
        String courseId,
        String startDate,
        String endDate,
        String status
) implements Identifiable {
    @Override
    public String id() {
        return openingId;
    }
}
