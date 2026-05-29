package com.oose.labsafety.waste.domain;

import com.oose.labsafety.common.Displayable;
import com.oose.labsafety.common.Identifiable;

import java.time.LocalDateTime;

public record WasteRecord(
    String categoryCode,
    String categoryName,
    String categoryType,
    String propertyInfo,
    String disposalMethod,
    String relatedLaw,
    String isActive,
    LocalDateTime createdAt
) implements Identifiable, Displayable {

    @Override
    public String id() {
        return categoryCode;
    }

    @Override
    public String summary() {
        return "분류코드=%s, 분류명=%s, 유형=%s, 성상=%s, 처리방법=%s, 관련법규=%s, 사용=%s, 등록일시=%s".formatted(
            categoryCode, categoryName, categoryType, propertyInfo, disposalMethod, relatedLaw, isActive, createdAt);
    }
}
