package com.oose.labsafety.waste.domain;

import com.oose.labsafety.common.domain.Identifiable;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record WasteCategory(
        @NotBlank(message = "폐기물 분류 ID는 필수입니다.") String categoryId,
        @NotBlank(message = "분류명은 필수입니다.") String categoryName,
        @NotBlank(message = "폐기물 유형은 필수입니다.") String categoryType,
        String characteristics,
        String disposalMethod,
        String relatedLaw,
        String useYn,
        String registeredAt
) implements Identifiable {
    public WasteCategory {
        registeredAt = registeredAt == null || registeredAt.isBlank()
                ? LocalDate.now().toString()
                : registeredAt;
    }

    @Override
    public String id() {
        return categoryId;
    }
}
