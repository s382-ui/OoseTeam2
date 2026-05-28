package com.oose.labsafety.laboratory.presentation;

import com.oose.labsafety.common.AbstractCatalogModule;
import com.oose.labsafety.common.ConsoleIO;
import com.oose.labsafety.laboratory.application.LaboratoryService;
import com.oose.labsafety.laboratory.domain.LaboratoryProfile;

import java.time.LocalDate;
import java.util.List;

public final class LaboratoryMenu extends AbstractCatalogModule<LaboratoryProfile> {

    public LaboratoryMenu(LaboratoryService service) {
        super("LAB", "연구실 관리", "연구실 기본 정보와 책임자 정보를 관리한다.", service);
    }

    @Override
    protected LaboratoryProfile createRecord(ConsoleIO io) {
        return new LaboratoryProfile(
                io.readRequiredLine("연구실 ID: "),
                io.readRequiredLine("연구실 명칭: "),
                io.readRequiredLine("책임자 이름: "),
                io.readRequiredLine("위치: "),
                io.readRequiredLine("안전등급: "),
                io.readRequiredLine("상태: "),
                io.readDate("등록일")
        );
    }

    @Override
    protected List<LaboratoryProfile> sampleRecords() {
        return List.of(
                new LaboratoryProfile("L-001", "분석화학실", "서가연", "본관 3층", "A", "ACTIVE", LocalDate.of(2026, 5, 7)),
                new LaboratoryProfile("L-002", "생명과학실", "김종규", "연구동 2층", "B", "ACTIVE", LocalDate.of(2026, 5, 8))
        );
    }
}