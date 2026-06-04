package com.oose.labsafety.education.presentation;

import com.oose.labsafety.common.ConsoleIO;
import com.oose.labsafety.common.FeatureModule;

public final class EducationMenu implements FeatureModule {

    private final ResearcherStandardRegisterView researcherStandardRegisterView;
    private final ResearcherStandardSearchView researcherStandardSearchView;
    private final CompletionResultRegisterView completionResultRegisterView;
    private final CompletionHistorySearchView completionHistorySearchView;

    public EducationMenu(
            ResearcherStandardRegisterView researcherStandardRegisterView,
            ResearcherStandardSearchView researcherStandardSearchView,
            CompletionResultRegisterView completionResultRegisterView,
            CompletionHistorySearchView completionHistorySearchView
    ) {
        this.researcherStandardRegisterView = researcherStandardRegisterView;
        this.researcherStandardSearchView = researcherStandardSearchView;
        this.completionResultRegisterView = completionResultRegisterView;
        this.completionHistorySearchView = completionHistorySearchView;
    }

    @Override
    public String code() {
        return "EDU";
    }

    @Override
    public String title() {
        return "안전교육 관리";
    }

    @Override
    public String description() {
        return "연구활동종사자 이수기준 설정과 교육이수 결과를 관리한다.";
    }

    @Override
    public void open(ConsoleIO io) {
        boolean running = true;
        while (running) {
            io.println("");
            io.println("[안전교육 관리]");
            io.println("1. 연구활동종사자 및 이수기준 설정 등록");
            io.println("2. 연구활동종사자 및 이수기준 설정 조회");
            io.println("3. 교육이수 결과 등록");
            io.println("4. 교육이수 내역 조회");
            io.println("0. 뒤로가기");

            int choice = io.readInt("선택: ", 0, 4);
            switch (choice) {
                case 1 -> researcherStandardRegisterView.show(io);
                case 2 -> researcherStandardSearchView.show(io);
                case 3 -> completionResultRegisterView.show(io);
                case 4 -> completionHistorySearchView.show(io);
                case 0 -> running = false;
                default -> {
                }
            }
        }
    }
}
