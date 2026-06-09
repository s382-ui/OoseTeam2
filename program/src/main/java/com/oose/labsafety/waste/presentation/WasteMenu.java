package com.oose.labsafety.waste.presentation;

import com.oose.labsafety.common.AbstractCatalogModule;
import com.oose.labsafety.common.ConsoleIO;
import com.oose.labsafety.waste.service.WasteService;
import com.oose.labsafety.waste.domain.WasteRecord;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public final class WasteMenu extends AbstractCatalogModule<WasteRecord> {

    public WasteMenu(WasteService service) {
        super("WST", "폐기물 관리", "폐기물(폐수) 분류 등록/조회와 처리 기준 정보를 관리한다.", service);
    }

    @Override
    protected WasteRecord createRecord(ConsoleIO io) {
        return new WasteRecord(
            io.readRequiredLine("폐기물 ID: "),
            io.readRequiredLine("발생 연구실 ID: "),
            io.readRequiredLine("폐기물 종류: "),
            new BigDecimal(io.readRequiredLine("수량: ")),
            io.readRequiredLine("단위: "),
            io.readDate("처리(예정)일"),
            io.readRequiredLine("처리 상태(대기/처리중/완료): "),
            io.readRequiredLine("처리 담당자 ID: "),
            io.readDateTime("등록 일시")
        );
    }

    @Override
    protected List<WasteRecord> sampleRecords() {
        return List.of(
            new WasteRecord("WASTE001", "LAB001", "폐유기용매", new BigDecimal("5.00"), "L", LocalDate.of(2026, 6, 30), "대기", "admin001", LocalDateTime.of(2026, 5, 10, 13, 0)),
            new WasteRecord("WASTE002", "LAB001", "폐산", new BigDecimal("2.00"), "L", LocalDate.of(2026, 5, 31), "완료", "admin001", LocalDateTime.of(2026, 5, 11, 13, 30))
        );
    }
}
