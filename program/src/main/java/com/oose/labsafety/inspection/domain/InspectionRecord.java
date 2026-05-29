package com.oose.labsafety.inspection.domain;

import com.oose.labsafety.common.Displayable;
import com.oose.labsafety.common.Identifiable;

import java.time.LocalDateTime;

public record InspectionRecord(
    String categoryCode,
    String categoryName,
    String categoryDetail,
    boolean useYn,
    LocalDateTime createdAt
) implements Identifiable, Displayable {

    @Override
    public String id() {
        return categoryCode;
    }

    @Override
    public String summary() {
        return "분류코드=%s, 분류명=%s, 상세=%s, 사용여부=%s, 등록일시=%s".formatted(
            categoryCode, categoryName, categoryDetail, useYn, createdAt);
    }
}