package com.oose.labsafety.education.presentation;

import com.oose.labsafety.common.AbstractCatalogModule;
import com.oose.labsafety.common.ConsoleIO;
import com.oose.labsafety.education.application.EducationService;
import com.oose.labsafety.education.domain.EducationRecord;

import java.time.LocalDate;
import java.util.List;

public final class EducationMenu extends AbstractCatalogModule<EducationRecord> {

    public EducationMenu(EducationService service) {
        super("EDU", "안전교육 관리", "교육 이수와 교육 상태를 관리한다.", service);
    }

    @Override
    protected EducationRecord createRecord(ConsoleIO io) {
        return new EducationRecord(
                io.readRequiredLine("교육 ID: "),
                io.readRequiredLine("대상자: "),
                io.readRequiredLine("교육 과정명: "),
                io.readRequiredLine("부서: "),
                io.readDate("이수일"),
                io.readRequiredLine("이수 상태: ")
        );
    }

    @Override
    protected List<EducationRecord> sampleRecords() {
        return List.of(
                new EducationRecord("E-001", "서가연", "연구실 안전 기본교육", "안전관리팀", LocalDate.of(2026, 5, 14), "COMPLETED"),
                new EducationRecord("E-002", "김종규", "화학물질 취급교육", "연구실 운영팀", LocalDate.of(2026, 5, 15), "COMPLETED")
        );
    }
}