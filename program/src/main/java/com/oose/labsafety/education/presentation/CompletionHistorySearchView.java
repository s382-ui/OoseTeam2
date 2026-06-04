package com.oose.labsafety.education.presentation;

import com.oose.labsafety.common.ConsoleIO;
import com.oose.labsafety.education.application.EducationCompletionControl;
import com.oose.labsafety.education.domain.EducationCompletionResult;

import java.util.List;

public final class CompletionHistorySearchView {

    private final EducationCompletionControl control;

    public CompletionHistorySearchView(EducationCompletionControl control) {
        this.control = control;
    }

    public void show(ConsoleIO io) {
        io.println("");
        io.println("[교육이수 내역 조회]");
        String keyword = initSearchForm(io);
        List<EducationCompletionResult> results = onSearch(keyword);
        displayCompletionHistoryList(io, results);
        showCompletionHistoryDetail(io);
        clearSearchCondition();
        io.pause();
    }

    private String initSearchForm(ConsoleIO io) {
        return io.readLine("검색어(전체 조회는 Enter): ");
    }

    private List<EducationCompletionResult> onSearch(String keyword) {
        return control.searchCompletionHistory(keyword);
    }

    private void displayCompletionHistoryList(ConsoleIO io, List<EducationCompletionResult> results) {
        if (results.isEmpty()) {
            io.println("조회된 교육이수 내역이 없습니다.");
            return;
        }
        for (int index = 0; index < results.size(); index++) {
            io.println((index + 1) + ". " + results.get(index).summary());
        }
    }

    private void showCompletionHistoryDetail(ConsoleIO io) {
        String completionResultId = io.readLine("상세 조회할 교육이수결과 ID(건너뛰기는 Enter): ");
        if (completionResultId.isBlank()) {
            return;
        }
        EducationCompletionResult detail = control.getCompletionResultDetail(completionResultId);
        io.println(detail == null ? "해당 교육이수결과를 찾을 수 없습니다." : detail.summary());
    }

    private void clearSearchCondition() {
        // Console input is one-shot, so no UI state remains to clear.
    }
}
