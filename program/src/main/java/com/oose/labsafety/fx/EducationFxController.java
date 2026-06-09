package com.oose.labsafety.fx;

import com.oose.labsafety.education.service.EducationCompletionControl;
import com.oose.labsafety.education.service.EducationReferenceControl;
import com.oose.labsafety.education.service.ResearcherStandardControl;
import com.oose.labsafety.education.domain.CompletionStandard;
import com.oose.labsafety.education.domain.EducationCompletionResult;
import com.oose.labsafety.education.domain.EducationOpening;
import com.oose.labsafety.education.domain.ResearcherCategory;
import com.oose.labsafety.education.infrastructure.EducationRepository;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.List;

public final class EducationFxController {

    private final EducationRepository repository = new EducationRepository();
    private final ResearcherStandardControl researcherStandardControl = new ResearcherStandardControl(repository);
    private final EducationCompletionControl completionControl = new EducationCompletionControl(repository);
    private final EducationReferenceControl referenceControl = new EducationReferenceControl(repository);

    @FXML private TextField categoryIdField;
    @FXML private TextField categoryNameField;
    @FXML private TextField categoryDescriptionField;
    @FXML private TextField categoryActiveField;
    @FXML private TextField standardIdField;
    @FXML private TextField requiredHoursField;
    @FXML private TextField standardStartDateField;
    @FXML private TextField standardEndDateField;
    @FXML private TextArea standardOutputArea;

    @FXML private TextField standardSearchField;
    @FXML private TextArea standardSearchOutputArea;

    @FXML private TextField completionIdField;
    @FXML private TextField researcherIdField;
    @FXML private TextField openingIdField;
    @FXML private TextField learningResultIdField;
    @FXML private TextField logIdField;
    @FXML private TextField completionDateField;
    @FXML private TextField recognizedHoursField;
    @FXML private TextField completionStatusField;
    @FXML private TextArea completionOutputArea;

    @FXML private TextField historySearchField;
    @FXML private TextField historyDetailIdField;
    @FXML private TextArea historyOutputArea;

    @FXML
    private void initialize() {
        categoryActiveField.setText("Y");
        learningResultIdField.setText("N/A");
        logIdField.setText("N/A");
        completionStatusField.setText("이수");
        loadEducationOpenings();
    }

    @FXML
    private void registerResearcherStandard() {
        try {
            ResearcherCategory category = new ResearcherCategory(
                    categoryIdField.getText().trim(),
                    categoryNameField.getText().trim(),
                    categoryDescriptionField.getText().trim(),
                    "Y".equalsIgnoreCase(categoryActiveField.getText().trim())
            );
            CompletionStandard standard = new CompletionStandard(
                    standardIdField.getText().trim(),
                    category.researcherCategoryId(),
                    Integer.parseInt(requiredHoursField.getText().trim()),
                    LocalDate.parse(standardStartDateField.getText().trim()),
                    LocalDate.parse(standardEndDateField.getText().trim())
            );

            researcherStandardControl.registerResearcherCategory(category);
            researcherStandardControl.registerCompletionStandard(standard);
            standardOutputArea.setText("등록 완료\n" + category.summary() + "\n" + standard.summary());
        } catch (RuntimeException exception) {
            standardOutputArea.setText(errorMessage(exception));
        }
    }

    @FXML
    private void searchResearcherStandard() {
        try {
            String keyword = standardSearchField.getText().trim();
            StringBuilder builder = new StringBuilder();
            builder.append("[연구활동종사자 분류]\n");
            researcherStandardControl.searchResearcherCategory(keyword)
                    .forEach(category -> builder.append(category.summary()).append('\n'));
            builder.append("\n[이수기준]\n");
            researcherStandardControl.searchCompletionStandard(keyword)
                    .forEach(standard -> builder.append(standard.summary()).append('\n'));
            standardSearchOutputArea.setText(builder.toString());
        } catch (RuntimeException exception) {
            standardSearchOutputArea.setText(errorMessage(exception));
        }
    }

    @FXML
    private void registerCompletionResult() {
        try {
            EducationCompletionResult result = new EducationCompletionResult(
                    completionIdField.getText().trim(),
                    researcherIdField.getText().trim(),
                    openingIdField.getText().trim(),
                    emptyAsNa(learningResultIdField.getText()),
                    emptyAsNa(logIdField.getText()),
                    LocalDate.parse(completionDateField.getText().trim()),
                    Integer.parseInt(recognizedHoursField.getText().trim()),
                    completionStatusField.getText().trim()
            );
            completionControl.registerCompletionResult(result);
            completionOutputArea.setText("등록 완료\n" + result.summary());
        } catch (RuntimeException exception) {
            completionOutputArea.setText(errorMessage(exception));
        }
    }

    @FXML
    private void loadEducationOpenings() {
        try {
            List<EducationOpening> openings = referenceControl.searchEducationOpening("");
            StringBuilder builder = new StringBuilder("[교육개설정보]\n");
            openings.forEach(opening -> builder.append(opening.summary()).append('\n'));
            completionOutputArea.setText(builder.toString());
        } catch (RuntimeException exception) {
            completionOutputArea.setText(errorMessage(exception));
        }
    }

    @FXML
    private void searchCompletionHistory() {
        try {
            String keyword = historySearchField.getText().trim();
            StringBuilder builder = new StringBuilder();
            completionControl.searchCompletionHistory(keyword)
                    .forEach(result -> builder.append(result.summary()).append('\n'));
            historyOutputArea.setText(builder.isEmpty() ? "조회된 교육이수 내역이 없습니다." : builder.toString());
        } catch (RuntimeException exception) {
            historyOutputArea.setText(errorMessage(exception));
        }
    }

    @FXML
    private void showCompletionDetail() {
        try {
            EducationCompletionResult result = completionControl.getCompletionResultDetail(historyDetailIdField.getText().trim());
            historyOutputArea.setText(result == null ? "해당 교육이수결과를 찾을 수 없습니다." : result.summary());
        } catch (RuntimeException exception) {
            historyOutputArea.setText(errorMessage(exception));
        }
    }

    private String emptyAsNa(String value) {
        return value == null || value.isBlank() ? "N/A" : value.trim();
    }

    private String errorMessage(Throwable throwable) {
        Throwable current = throwable;
        while (current.getCause() != null) {
            current = current.getCause();
        }
        return throwable.getMessage() + "\n" + current.getMessage();
    }
}
