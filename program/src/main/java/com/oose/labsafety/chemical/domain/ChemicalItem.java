package com.oose.labsafety.chemical.domain;

import com.oose.labsafety.common.Displayable;
import com.oose.labsafety.common.Identifiable;

public record ChemicalItem(
        String chemicalId,
        String chemicalName,
        String hazardClass,
        String storageLocation,
        int quantity,
        String unit,
        String status
) implements Identifiable, Displayable {

    @Override
    public String id() {
        return chemicalId;
    }

    @Override
    public String summary() {
        return "화학물질ID=%s, 명칭=%s, 분류=%s, 위치=%s, 수량=%d%s, 상태=%s".formatted(
                chemicalId, chemicalName, hazardClass, storageLocation, quantity, unit, status);
    }
}