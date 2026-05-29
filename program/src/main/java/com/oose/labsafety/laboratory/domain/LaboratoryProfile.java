package com.oose.labsafety.laboratory.domain;

import com.oose.labsafety.common.Displayable;
import com.oose.labsafety.common.Identifiable;

import java.time.LocalDateTime;

public record LaboratoryProfile(
    String labId,
    String labName,
    String buildingName,
    String floor,
    String roomNo,
    String departmentName,
    String managerId,
        String managerName,
    String contactNo,
    String managementGrade,
    String isActive,
    LocalDateTime createdAt
) implements Identifiable, Displayable {

    @Override
    public String id() {
        return labId;
    }

    @Override
    public String summary() {
        return "연구실ID=%s, 명칭=%s, 위치=%s %s층 %s호, 소속=%s, 책임자=%s(%s), 등급=%s, 사용=%s".formatted(
            labId, labName, buildingName, floor, roomNo, departmentName, managerName, managerId, managementGrade, isActive);
    }
}