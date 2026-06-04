package com.oose.labsafety.chemical.domain;

import com.oose.labsafety.common.Displayable;
import com.oose.labsafety.common.Identifiable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ChemicalItem(
    String chemicalId,
    String labId,
    String chemicalName,
    String casNumber,
    BigDecimal quantity,
    String unit,
    String hazardGrade,
    String storageMethod,
    LocalDate expiryDate,
    LocalDateTime createdAt
) implements Identifiable, Displayable {

    @Override
    public String id() {
        return chemicalId;
    }

    @Override
    public String summary() {
        return "화학물질ID=%s, 연구실ID=%s, 명칭=%s, CAS=%s, 보유량=%s%s, 위험등급=%s, 보관방법=%s, 유효기간=%s, 등록일시=%s".formatted(
            chemicalId, labId, chemicalName, casNumber, quantity, unit, hazardGrade, storageMethod, expiryDate, createdAt);
    }
}
