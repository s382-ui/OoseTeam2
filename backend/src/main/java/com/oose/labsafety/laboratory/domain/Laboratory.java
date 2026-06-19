package com.oose.labsafety.laboratory.domain;

import com.oose.labsafety.common.domain.Identifiable;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record Laboratory(
        @NotBlank(message = "연구실 ID는 필수입니다.") String labId,
        @NotBlank(message = "연구실명은 필수입니다.") String labName,
        @NotBlank(message = "건물명은 필수입니다.") String buildingName,
        @NotBlank(message = "층은 필수입니다.") String floor,
        @NotBlank(message = "호실은 필수입니다.") String roomNo,
        @NotBlank(message = "소속 학과는 필수입니다.") String departmentName,
        @NotBlank(message = "연구실 책임자 ID는 필수입니다.") String managerId,
        @NotBlank(message = "연구실 책임자명은 필수입니다.") String managerName,
        String contactNo,
        @NotBlank(message = "관리 등급은 필수입니다.") String managementGrade,
        @NotBlank(message = "사용 여부는 필수입니다.") String isActive,
        String createdAt
) implements Identifiable {
    public Laboratory {
        createdAt = createdAt == null || createdAt.isBlank()
                ? LocalDate.now().toString()
                : createdAt;
    }

    @Override
    public String id() {
        return labId;
    }
}
