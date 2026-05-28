package com.oose.labsafety.waste.domain;

import com.oose.labsafety.common.Displayable;
import com.oose.labsafety.common.Identifiable;

import java.time.LocalDate;

public record WasteRecord(
        String wasteId,
        String wasteType,
        String sourceLaboratory,
        String storageLocation,
        String status,
        LocalDate recordedDate
) implements Identifiable, Displayable {

    @Override
    public String id() {
        return wasteId;
    }

    @Override
    public String summary() {
        return "폐기물ID=%s, 유형=%s, 발생연구실=%s, 위치=%s, 상태=%s, 기록일=%s".formatted(
                wasteId, wasteType, sourceLaboratory, storageLocation, status, recordedDate);
    }
}