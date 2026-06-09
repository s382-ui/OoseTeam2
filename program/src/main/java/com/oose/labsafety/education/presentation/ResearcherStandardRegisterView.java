package com.oose.labsafety.education.presentation;

import com.oose.labsafety.common.ConsoleIO;
import com.oose.labsafety.education.service.ResearcherStandardControl;
import com.oose.labsafety.education.domain.CompletionStandard;
import com.oose.labsafety.education.domain.ResearcherCategory;

public final class ResearcherStandardRegisterView {

    private final ResearcherStandardControl control;

    public ResearcherStandardRegisterView(ResearcherStandardControl control) {
        this.control = control;
    }

    public void show(ConsoleIO io) {
        io.println("");
        io.println("[연구활동종사자 및 이수기준 설정 등록]");
        initForm(io);
        try {
            ResearcherCategory category = loadCategoryForm(io);
            CompletionStandard standard = loadStandardForm(io, category.researcherCategoryId());
            onSubmit(category, standard);
            io.println("연구활동종사자 및 이수기준 설정 등록이 완료되었습니다.");
        } catch (IllegalStateException exception) {
            io.println(exception.getMessage());
            io.println(rootMessage(exception));
        }
        clearForm();
        io.pause();
    }

    private void initForm(ConsoleIO io) {
        io.println("분류 정보와 이수기준 정보를 입력합니다.");
    }

    private ResearcherCategory loadCategoryForm(ConsoleIO io) {
        return new ResearcherCategory(
                io.readRequiredLine("연구활동종사자 분류 ID: "),
                io.readRequiredLine("분류명: "),
                io.readRequiredLine("분류 설명: "),
                "Y".equalsIgnoreCase(io.readRequiredLine("사용 여부(Y/N): "))
        );
    }

    private CompletionStandard loadStandardForm(ConsoleIO io, String researcherCategoryId) {
        return new CompletionStandard(
                io.readRequiredLine("이수기준 ID: "),
                researcherCategoryId,
                io.readInt("필수 이수시간: ", 0, 1000),
                io.readDate("기준 적용 시작일"),
                io.readDate("기준 적용 종료일")
        );
    }

    private void onSubmit(ResearcherCategory category, CompletionStandard standard) {
        if (!control.registerResearcherCategory(category)) {
            throw new IllegalStateException("연구활동종사자 분류 입력값이 올바르지 않습니다.");
        }
        if (!control.registerCompletionStandard(standard)) {
            throw new IllegalStateException("이수기준 적용 기간이 올바르지 않습니다.");
        }
    }

    private void clearForm() {
        // Console input is one-shot, so no UI state remains to clear.
    }

    private String rootMessage(Throwable throwable) {
        Throwable current = throwable;
        while (current.getCause() != null) {
            current = current.getCause();
        }
        return current.getMessage();
    }
}
