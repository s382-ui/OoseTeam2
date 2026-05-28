package com.oose.labsafety.inspection.presentation;

import com.oose.labsafety.common.AbstractCatalogModule;
import com.oose.labsafety.common.ConsoleIO;
import com.oose.labsafety.inspection.application.InspectionService;
import com.oose.labsafety.inspection.domain.InspectionRecord;

import java.time.LocalDate;
import java.util.List;

public final class InspectionMenu extends AbstractCatalogModule<InspectionRecord> {

    public InspectionMenu(InspectionService service) {
        super("INSP", "점검 관리", "일상점검과 정기점검 흐름을 관리한다.", service);
    }

    @Override
    protected InspectionRecord createRecord(ConsoleIO io) {
        return new InspectionRecord(
                io.readRequiredLine("점검 ID: "),
                io.readRequiredLine("점검 유형: "),
                io.readRequiredLine("대상 연구실: "),
                io.readDate("점검일"),
                io.readRequiredLine("결과 상태: "),
                io.readRequiredLine("점검자: ")
        );
    }

    @Override
    protected List<InspectionRecord> sampleRecords() {
        return List.of(
                new InspectionRecord("I-001", "일상점검", "분석화학실", LocalDate.of(2026, 5, 12), "PASS", "서성훈"),
                new InspectionRecord("I-002", "정기점검", "생명과학실", LocalDate.of(2026, 5, 13), "PASS", "최남규")
        );
    }
}