package com.oose.labsafety.inspection.presentation;

import com.oose.labsafety.common.AbstractCatalogModule;
import com.oose.labsafety.common.ConsoleIO;
import com.oose.labsafety.inspection.service.InspectionService;
import com.oose.labsafety.inspection.domain.InspectionRecord;

import java.time.LocalDateTime;
import java.util.List;

public final class InspectionMenu extends AbstractCatalogModule<InspectionRecord> {

    public InspectionMenu(InspectionService service) {
        super("INSP", "점검 관리", "점검분야 분류 등록/조회와 사용여부 관리를 수행한다.", service);
    }

    @Override
    protected InspectionRecord createRecord(ConsoleIO io) {
        return new InspectionRecord(
            io.readRequiredLine("분류 코드: "),
            io.readRequiredLine("분류명: "),
            io.readRequiredLine("상세 설명: "),
            "Y".equalsIgnoreCase(io.readRequiredLine("사용 여부(Y/N): ")),
            io.readDateTime("등록 일시")
        );
    }

    @Override
    protected List<InspectionRecord> sampleRecords() {
        return List.of(
            new InspectionRecord("IC001", "화재·폭발 예방", "화기, 소화기, 전기 안전 관련 점검", true, LocalDateTime.of(2026, 5, 12, 14, 0)),
            new InspectionRecord("IC002", "화학물질 안전", "화학물질 보관·취급 상태 점검", true, LocalDateTime.of(2026, 5, 13, 14, 30))
        );
    }
}
