package com.oose.labsafety.chemical.domain;

import com.oose.labsafety.common.domain.Identifiable;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record Chemical(
        @NotBlank(message = "화학물질 ID는 필수입니다.") String chemicalId,
        @NotBlank(message = "제조사명은 필수입니다.") String manufacturerName,
        @NotBlank(message = "화학물질명은 필수입니다.") String chemicalName,
        String casNumber,
        String contentRate,
        String msdsPath,
        String analysisPath,
        String status,
        String createdAt
) implements Identifiable {
    public Chemical {
        status = status == null || status.isBlank() ? "ACTIVE" : status;
        createdAt = createdAt == null || createdAt.isBlank()
                ? LocalDate.now().toString()
                : createdAt;
    }

    @Override
    public String id() {
        return chemicalId;
    }
}
