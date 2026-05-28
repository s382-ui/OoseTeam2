package com.oose.labsafety.inspection.domain;

import com.oose.labsafety.common.Displayable;
import com.oose.labsafety.common.Identifiable;

import java.time.LocalDate;

public record InspectionRecord(
        String inspectionId,
        String inspectionType,
        String targetLaboratory,
        LocalDate inspectionDate,
        String resultStatus,
        String inspectorName
) implements Identifiable, Displayable {

    @Override
    public String id() {
        return inspectionId;
    }

    @Override
    public String summary() {
        return "점검ID=%s, 유형=%s, 대상=%s, 점검일=%s, 결과=%s, 점검자=%s".formatted(
                inspectionId, inspectionType, targetLaboratory, inspectionDate, resultStatus, inspectorName);
    }
}