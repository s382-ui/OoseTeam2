package com.oose.labsafety.waste.presentation;

import com.oose.labsafety.common.AbstractCatalogModule;
import com.oose.labsafety.common.ConsoleIO;
import com.oose.labsafety.waste.application.WasteService;
import com.oose.labsafety.waste.domain.WasteRecord;

import java.time.LocalDateTime;
import java.util.List;

public final class WasteMenu extends AbstractCatalogModule<WasteRecord> {

    public WasteMenu(WasteService service) {
        super("WST", "폐기물 관리", "폐기물(폐수) 분류 등록/조회와 처리 기준 정보를 관리한다.", service);
    }

    @Override
    protected WasteRecord createRecord(ConsoleIO io) {
        return new WasteRecord(
            io.readRequiredLine("분류 코드: "),
            io.readRequiredLine("분류명: "),
            io.readRequiredLine("분류 유형(지정/일반): "),
            io.readRequiredLine("성상 정보: "),
            io.readRequiredLine("위탁 처리 방법: "),
            io.readRequiredLine("관련 법규: "),
            io.readRequiredLine("사용 여부(Y/N): "),
            io.readDateTime("등록 일시")
        );
    }

    @Override
    protected List<WasteRecord> sampleRecords() {
        return List.of(
            new WasteRecord("WST-001", "유기용제 폐액", "지정", "액상", "전문업체 위탁", "폐기물관리법", "Y", LocalDateTime.of(2026, 5, 10, 13, 0)),
            new WasteRecord("WST-002", "일반 실험폐기물", "일반", "고형", "분리배출 후 위탁", "자원순환법", "Y", LocalDateTime.of(2026, 5, 11, 13, 30))
        );
    }
}