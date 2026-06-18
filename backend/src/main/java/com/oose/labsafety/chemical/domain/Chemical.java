package com.oose.labsafety.chemical.domain;

import com.oose.labsafety.common.domain.Identifiable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record Chemical(
        @NotBlank(message = "화학물질 ID는 필수입니다.") String chemicalId,
        @NotBlank(message = "화학물질명은 필수입니다.") String chemicalName,
        String casNumber,
        String manufacturerName,
        @PositiveOrZero(message = "수량은 0 이상이어야 합니다.") double quantity,
        String unit,
        String storageLocation,
        String msdsYn,
        String status,
        String registeredAt
) implements Identifiable {
    public Chemical {
        registeredAt = registeredAt == null || registeredAt.isBlank()
                ? LocalDate.now().toString()
                : registeredAt;
    }

    @Override
    public String id() {
        return chemicalId;
    }
}
