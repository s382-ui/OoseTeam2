package com.oose.labsafety.chemical.presentation;

import com.oose.labsafety.chemical.application.ChemicalService;
import com.oose.labsafety.chemical.domain.ChemicalItem;
import com.oose.labsafety.common.AbstractCatalogModule;
import com.oose.labsafety.common.ConsoleIO;

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
            io.readRequiredLine("제조사명: "),
                io.readRequiredLine("명칭: "),
            io.readRequiredLine("CAS 번호: "),
            io.readRequiredLine("성분 함유량: "),
            io.readRequiredLine("MSDS 경로: "),
            io.readRequiredLine("성분분석표 경로: "),
            io.readRequiredLine("상태(ACTIVE/INACTIVE): "),
            io.readDateTime("등록 일시")
        );
    }

    @Override
    protected List<ChemicalItem> sampleRecords() {
        return List.of(
            new ChemicalItem("CHEM-001", "K-Lab", "에탄올", "64-17-5", "95%", "/msds/ethanol.pdf", "/analysis/ethanol.pdf", "ACTIVE", LocalDateTime.of(2026, 5, 10, 11, 0)),
            new ChemicalItem("CHEM-002", "BioChem", "염산", "7647-01-0", "35%", "/msds/hcl.pdf", "/analysis/hcl.pdf", "ACTIVE", LocalDateTime.of(2026, 5, 11, 11, 30))
        );
    }
}