package com.oose.labsafety.education.presentation;

import com.oose.labsafety.common.AbstractCatalogModule;
import com.oose.labsafety.common.ConsoleIO;
import com.oose.labsafety.education.application.EducationService;
import com.oose.labsafety.education.domain.EducationRecord;

import java.time.LocalDate;
import java.util.List;

public final class EducationMenu extends AbstractCatalogModule<EducationRecord> {

    public EducationMenu(EducationService service) {
        super("EDU", "안전교육 관리", "교육이수 결과 등록/조회와 수료증 발급 기반 데이터를 관리한다.", service);
    }

    @Override
    protected EducationRecord createRecord(ConsoleIO io) {
        return new EducationRecord(
                io.readRequiredLine("교육이수결과 ID: "),
                io.readRequiredLine("연구활동종사자 ID: "),
                io.readRequiredLine("교육개설정보 ID: "),
                io.readRequiredLine("학습결과 ID(없으면 N/A): "),
                io.readRequiredLine("안전교육일지 ID(없으면 N/A): "),
                io.readDate("이수일"),
                io.readInt("인정시간: ", 0, 1000),
                io.readRequiredLine("이수 상태: "),
                "Y".equalsIgnoreCase(io.readRequiredLine("수동 등록 여부(Y/N): "))
        );
    }

    @Override
    protected List<EducationRecord> sampleRecords() {
        return List.of(
            new EducationRecord("COMP-001", "20260001", "OPEN-001", "LR-001", "N/A", LocalDate.of(2026, 5, 14), 4, "이수", false),
            new EducationRecord("COMP-002", "20260002", "OPEN-002", "N/A", "LOG-002", LocalDate.of(2026, 5, 15), 2, "이수", true)
        );
    }
}