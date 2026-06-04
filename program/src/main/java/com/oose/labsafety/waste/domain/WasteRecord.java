package com.oose.labsafety.waste.domain;

import com.oose.labsafety.common.Displayable;
import com.oose.labsafety.common.Identifiable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record WasteRecord(
    String wasteId,
    String labId,
    String wasteType,
    BigDecimal quantity,
    String unit,
    LocalDate disposalDate,
    String status,
    String handlerId,
    LocalDateTime createdAt
) implements Identifiable, Displayable {

    @Override
    public String id() {
        return wasteId;
    }

    @Override
    public String summary() {
        return "폐기물ID=%s, 연구실ID=%s, 종류=%s, 수량=%s%s, 처리일=%s, 상태=%s, 담당자ID=%s, 등록일시=%s".formatted(
            wasteId, labId, wasteType, quantity, unit, disposalDate, status, handlerId, createdAt);
    }
}
