package com.oose.labsafety.chemical.presentation;

import com.oose.labsafety.chemical.application.ChemicalService;
import com.oose.labsafety.chemical.domain.ChemicalItem;
import com.oose.labsafety.common.AbstractCatalogModule;
import com.oose.labsafety.common.ConsoleIO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public final class ChemicalMenu extends AbstractCatalogModule<ChemicalItem> {

    public ChemicalMenu(ChemicalService service) {
        super("CHEM", "화학물질 관리", "화학물질 마스터 등록/조회와 MSDS 연계 정보를 관리한다.", service);
    }

    @Override
    protected ChemicalItem createRecord(ConsoleIO io) {
        return new ChemicalItem(
            io.readRequiredLine("화학물질 ID: "),
            io.readRequiredLine("보관 연구실 ID: "),
            io.readRequiredLine("명칭: "),
            io.readRequiredLine("CAS 번호: "),
            new BigDecimal(io.readRequiredLine("보유량: ")),
            io.readRequiredLine("단위: "),
            io.readRequiredLine("위험등급: "),
            io.readRequiredLine("보관방법: "),
            io.readDate("유효기간"),
            io.readDateTime("등록 일시")
        );
    }

    @Override
    protected List<ChemicalItem> sampleRecords() {
        return List.of(
            new ChemicalItem("CHEM001", "LAB001", "에탄올", "64-17-5", new BigDecimal("500.00"), "mL", "2등급", "상온 밀폐 보관", LocalDate.of(2027, 12, 31), LocalDateTime.of(2026, 5, 10, 11, 0)),
            new ChemicalItem("CHEM002", "LAB001", "염산", "7647-01-0", new BigDecimal("200.00"), "mL", "1등급", "내산성 캐비닛 보관", LocalDate.of(2026, 12, 31), LocalDateTime.of(2026, 5, 11, 11, 30))
        );
    }
}
