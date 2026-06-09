package com.oose.labsafety.education.presentation;

import com.oose.labsafety.common.ConsoleIO;
import com.oose.labsafety.education.service.ResearcherStandardControl;
import com.oose.labsafety.education.domain.CompletionStandard;
import com.oose.labsafety.education.domain.ResearcherCategory;

import java.util.List;

public final class ResearcherStandardSearchView {

    private final ResearcherStandardControl control;

    public ResearcherStandardSearchView(ResearcherStandardControl control) {
        this.control = control;
    }

    public void show(ConsoleIO io) {
        io.println("");
        io.println("[연구활동종사자 및 이수기준 설정 조회]");
        String keyword = initSearchForm(io);
        onSearch(io, keyword);
        clearSearchCondition();
        io.pause();
    }

    private String initSearchForm(ConsoleIO io) {
        return io.readLine("검색어(전체 조회는 Enter): ");
    }

    private void onSearch(ConsoleIO io, String keyword) {
        displaySearchResult(io, control.searchResearcherCategory(keyword), control.searchCompletionStandard(keyword));
    }

    private void displaySearchResult(ConsoleIO io, List<ResearcherCategory> categories, List<CompletionStandard> standards) {
        io.println("");
        io.println("연구활동종사자 분류");
        if (categories.isEmpty()) {
            io.println("조회된 분류가 없습니다.");
        } else {
            for (int index = 0; index < categories.size(); index++) {
                io.println((index + 1) + ". " + categories.get(index).summary());
            }
        }

        io.println("");
        io.println("이수기준");
        if (standards.isEmpty()) {
            io.println("조회된 이수기준이 없습니다.");
        } else {
            for (int index = 0; index < standards.size(); index++) {
                io.println((index + 1) + ". " + standards.get(index).summary());
            }
        }
    }

    public void showStandardDetail(ConsoleIO io, String keyword) {
        displaySearchResult(io, List.of(), control.searchCompletionStandard(keyword));
    }

    private void clearSearchCondition() {
        // Console input is one-shot, so no UI state remains to clear.
    }
}
