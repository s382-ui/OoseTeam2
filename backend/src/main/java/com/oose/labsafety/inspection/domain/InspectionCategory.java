package com.oose.labsafety.inspection.domain;

import com.oose.labsafety.common.domain.Identifiable;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record InspectionCategory(
        @NotBlank(message = "점검분야 분류 코드는 필수입니다.") String categoryCode,
        @NotBlank(message = "점검분야명은 필수입니다.") String categoryName,
        String categoryDetail,
        boolean useYn,
        String createdAt
) implements Identifiable {
    public InspectionCategory {
        createdAt = createdAt == null || createdAt.isBlank()
                ? LocalDate.now().toString()
                : createdAt;
    }

    @Override
    public String id() {
        return categoryCode;
    }
}
