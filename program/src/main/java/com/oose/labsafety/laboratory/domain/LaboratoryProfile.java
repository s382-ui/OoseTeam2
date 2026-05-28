package com.oose.labsafety.laboratory.domain;

import com.oose.labsafety.common.Displayable;
import com.oose.labsafety.common.Identifiable;

import java.time.LocalDate;

public record LaboratoryProfile(
        String laboratoryId,
        String laboratoryName,
        String managerName,
        String location,
        String safetyGrade,
        String status,
        LocalDate registeredDate
) implements Identifiable, Displayable {

    @Override
    public String id() {
        return laboratoryId;
    }

    @Override
    public String summary() {
        return "연구실ID=%s, 명칭=%s, 책임자=%s, 위치=%s, 등급=%s, 상태=%s".formatted(
                laboratoryId, laboratoryName, managerName, location, safetyGrade, status);
    }
}