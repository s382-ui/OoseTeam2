package com.oose.labsafety.chemical.presentation;

import com.oose.labsafety.chemical.application.ChemicalService;
import com.oose.labsafety.chemical.domain.ChemicalItem;
import com.oose.labsafety.common.AbstractCatalogModule;
import com.oose.labsafety.common.ConsoleIO;

import java.util.List;

public final class ChemicalMenu extends AbstractCatalogModule<ChemicalItem> {

    public ChemicalMenu(ChemicalService service) {
        super("CHEM", "화학물질 관리", "화학물질 등록, 보관, 상태를 관리한다.", service);
    }

    @Override
    protected ChemicalItem createRecord(ConsoleIO io) {
        return new ChemicalItem(
                io.readRequiredLine("화학물질 ID: "),
                io.readRequiredLine("명칭: "),
                io.readRequiredLine("위험 분류: "),
                io.readRequiredLine("보관 위치: "),
                io.readInt("수량: ", 0, Integer.MAX_VALUE),
                io.readRequiredLine("단위: "),
                io.readRequiredLine("상태: ")
        );
    }

    @Override
    protected List<ChemicalItem> sampleRecords() {
        return List.of(
                new ChemicalItem("C-001", "에탄올", "인화성", "화학보관실 A", 20, "L", "STORED"),
                new ChemicalItem("C-002", "염산", "부식성", "화학보관실 B", 15, "L", "STORED")
        );
    }
}