package com.oose.labsafety.education.presentation;

import com.oose.labsafety.common.ConsoleIO;
import com.oose.labsafety.education.service.EducationCompletionControl;
import com.oose.labsafety.education.service.EducationReferenceControl;
import com.oose.labsafety.education.domain.EducationCompletionResult;
import com.oose.labsafety.education.domain.EducationOpening;

import java.util.List;

public final class CompletionResultRegisterView {

    private final EducationCompletionControl completionControl;
    private final EducationReferenceControl referenceControl;

    public CompletionResultRegisterView(EducationCompletionControl completionControl, EducationReferenceControl referenceControl) {
        this.completionControl = completionControl;
        this.referenceControl = referenceControl;
    }

    public void show(ConsoleIO io) {
        io.println("");
        io.println("[교육이수 결과 등록]");
        initForm(io);
        loadEducationReference(io);
        try {
            EducationCompletionResult result = collectInputData(io);
            onSubmit(result);
            displayRegisterResult(io, true);
        } catch (IllegalStateException exception) {
            displayRegisterResult(io, false);
            io.println(exception.getMessage());
            io.println(rootMessage(exception));
        }
        clearForm();
        io.pause();
    }

    private void initForm(ConsoleIO io) {
        io.println("교육이수결과 등록에 필요한 정보를 입력합니다.");
    }

    private void loadEducationReference(ConsoleIO io) {
        io.println("");
        io.println("등록 가능한 교육개설정보");
        List<EducationOpening> openings = referenceControl.searchEducationOpening("");
        for (int index = 0; index < openings.size(); index++) {
            io.println((index + 1) + ". " + openings.get(index).summary());
        }
        io.println("");
    }

    private EducationCompletionResult collectInputData(ConsoleIO io) {
        return new EducationCompletionResult(
                io.readRequiredLine("교육이수결과 ID: "),
                io.readRequiredLine("연구활동종사자 ID: "),
                io.readRequiredLine("교육개설정보 ID: "),
                io.readRequiredLine("학습결과 ID(없으면 N/A): "),
                io.readRequiredLine("안전교육일지 ID(없으면 N/A): "),
                io.readDate("이수일"),
                io.readInt("인정시간: ", 0, 1000),
                io.readRequiredLine("이수 상태: ")
        );
    }

    private void onSubmit(EducationCompletionResult result) {
        if (!completionControl.registerCompletionResult(result)) {
            throw new IllegalStateException("교육이수결과 입력값 또는 교육개설정보 상태가 올바르지 않습니다.");
        }
    }

    private void displayRegisterResult(ConsoleIO io, boolean success) {
        io.println(success ? "교육이수 결과 등록이 완료되었습니다." : "교육이수 결과 등록에 실패했습니다.");
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
