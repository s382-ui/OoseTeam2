package com.oose.labsafety.waste.domain;

import com.oose.labsafety.common.domain.Identifiable;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record WasteCategory(
        @NotBlank(message = "폐기물 분류 코드는 필수입니다.") String categoryCode,
        @NotBlank(message = "분류명은 필수입니다.") String categoryName,
        @NotBlank(message = "폐기물 유형은 필수입니다.") String categoryType,
        String propertyInfo,
        String disposalMethod,
        String relatedLaw,
        @NotBlank(message = "사용 여부는 필수입니다.") String isActive,
        String createdAt
) implements Identifiable {
    public WasteCategory {
        createdAt = createdAt == null || createdAt.isBlank()
                ? LocalDate.now().toString()
                : createdAt;
    }

    @Override
    public String id() {
        return categoryCode;
    }
}
