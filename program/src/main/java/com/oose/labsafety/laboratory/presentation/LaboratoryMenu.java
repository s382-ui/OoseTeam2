package com.oose.labsafety.laboratory.presentation;

import com.oose.labsafety.common.AbstractCatalogModule;
import com.oose.labsafety.common.ConsoleIO;
import com.oose.labsafety.laboratory.service.LaboratoryService;
import com.oose.labsafety.laboratory.domain.LaboratoryProfile;

import java.time.LocalDateTime;
import java.util.List;

public final class LaboratoryMenu extends AbstractCatalogModule<LaboratoryProfile> {

    public LaboratoryMenu(LaboratoryService service) {
        super("LAB", "연구실 관리", "연구실 등록/조회 및 위치 중복 방지를 위한 기본 정보를 관리한다.", service);
    }

    @Override
    protected LaboratoryProfile createRecord(ConsoleIO io) {
        return new LaboratoryProfile(
            io.readRequiredLine("연구실 ID: "),
            io.readRequiredLine("연구실 명칭: "),
            io.readRequiredLine("건물명: "),
            io.readRequiredLine("층: "),
            io.readRequiredLine("호실: "),
            io.readRequiredLine("소속 학과/부서: "),
            io.readRequiredLine("책임자 사용자 ID: "),
            io.readRequiredLine("책임자 이름: "),
            io.readRequiredLine("연락처: "),
            io.readRequiredLine("관리 등급: "),
            io.readRequiredLine("사용 여부(Y/N): "),
            io.readDateTime("등록 일시")
        );
    }

    @Override
    protected List<LaboratoryProfile> sampleRecords() {
        return List.of(
            new LaboratoryProfile("LAB001", "유기화학 연구실", "공학관", "3", "301호", "화학공학과", "admin001", "관리자", "02-111-1111", "A", "Y", LocalDateTime.of(2026, 5, 7, 10, 0)),
            new LaboratoryProfile("LAB002", "생명과학 연구실", "과학관", "2", "205호", "생명과학과", "admin001", "관리자", "02-222-2222", "B", "Y", LocalDateTime.of(2026, 5, 8, 10, 30))
        );
    }
}
