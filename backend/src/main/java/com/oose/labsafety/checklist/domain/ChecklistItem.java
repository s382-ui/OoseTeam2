package com.oose.labsafety.checklist.domain;

import com.oose.labsafety.common.domain.Identifiable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ChecklistItem(
        @NotBlank(message = "체크리스트 항목 ID는 필수입니다.") String itemId,
        @NotBlank(message = "점검 항목명은 필수입니다.") String itemName,
        @NotBlank(message = "점검 분야명은 필수입니다.") String categoryName,
        @Min(value = 0, message = "배점은 0 이상이어야 합니다.") int score,
        @NotBlank(message = "점검 유형은 필수입니다.") String inspectionType,
        boolean requiredYn,
        boolean useYn,
        String createdAt
) implements Identifiable {
    public ChecklistItem {
        createdAt = createdAt == null || createdAt.isBlank()
                ? LocalDate.now().toString()
                : createdAt;
    }

    @Override
    public String id() {
        return itemId;
    }
}
