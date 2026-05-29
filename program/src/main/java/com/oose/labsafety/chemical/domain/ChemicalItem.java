package com.oose.labsafety.chemical.domain;

import com.oose.labsafety.common.Displayable;
import com.oose.labsafety.common.Identifiable;

import java.time.LocalDateTime;

public record ChemicalItem(
        String chemicalId,
    String manufacturerName,
        String chemicalName,
    String casNumber,
    String contentRate,
    String msdsPath,
    String analysisPath,
    String status,
    LocalDateTime createdAt
) implements Identifiable, Displayable {

    @Override
    public String id() {
        return chemicalId;
    }

    @Override
    public String summary() {
        return "화학물질ID=%s, 제조사=%s, 명칭=%s, CAS=%s, 함유량=%s, 상태=%s".formatted(
            chemicalId, manufacturerName, chemicalName, casNumber, contentRate, status);
    }
}