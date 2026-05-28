package com.oose.labsafety.waste.presentation;

import com.oose.labsafety.common.AbstractCatalogModule;
import com.oose.labsafety.common.ConsoleIO;
import com.oose.labsafety.waste.application.WasteService;
import com.oose.labsafety.waste.domain.WasteRecord;

import java.time.LocalDate;
import java.util.List;

public final class WasteMenu extends AbstractCatalogModule<WasteRecord> {

    public WasteMenu(WasteService service) {
        super("WST", "폐기물 관리", "폐기물 발생과 보관, 처리 상태를 관리한다.", service);
    }

    @Override
    protected WasteRecord createRecord(ConsoleIO io) {
        return new WasteRecord(
                io.readRequiredLine("폐기물 ID: "),
                io.readRequiredLine("유형: "),
                io.readRequiredLine("발생 연구실: "),
                io.readRequiredLine("보관 위치: "),
                io.readRequiredLine("상태: "),
                io.readDate("기록일")
        );
    }

    @Override
    protected List<WasteRecord> sampleRecords() {
        return List.of(
                new WasteRecord("W-001", "액상폐기물", "분석화학실", "폐기물보관실 1", "STORED", LocalDate.of(2026, 5, 10)),
                new WasteRecord("W-002", "고형폐기물", "생명과학실", "폐기물보관실 2", "STORED", LocalDate.of(2026, 5, 11))
        );
    }
}