package com.oose.labsafety.inspection.domain;

import com.oose.labsafety.common.domain.Identifiable;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record InspectionCategory(
        @NotBlank(message = "점검분야 ID는 필수입니다.") String inspectionCategoryId,
        @NotBlank(message = "점검분야명은 필수입니다.") String categoryName,
        String description,
        String useYn,
        String registeredAt
) implements Identifiable {
    public InspectionCategory {
        registeredAt = registeredAt == null || registeredAt.isBlank()
                ? LocalDate.now().toString()
                : registeredAt;
    }

    @Override
    public String id() {
        return inspectionCategoryId;
    }
}
