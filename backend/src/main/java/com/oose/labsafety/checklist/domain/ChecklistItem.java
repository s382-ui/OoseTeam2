package com.oose.labsafety.checklist.domain;

import com.oose.labsafety.common.domain.Identifiable;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ChecklistItem(
        @NotBlank(message = "체크리스트 항목 ID는 필수입니다.") String itemId,
        @NotBlank(message = "점검 항목명은 필수입니다.") String itemName,
        String categoryName,
        String inspectionType,
        String requiredYn,
        String useYn,
        String description,
        String registeredAt
) implements Identifiable {
    public ChecklistItem {
        registeredAt = registeredAt == null || registeredAt.isBlank()
                ? LocalDate.now().toString()
                : registeredAt;
    }

    @Override
    public String id() {
        return itemId;
    }
}
