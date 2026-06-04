package com.oose.labsafety.education.application;

import com.oose.labsafety.education.domain.EducationCompletionResult;
import com.oose.labsafety.education.domain.EducationOpening;
import com.oose.labsafety.education.infrastructure.EducationRepository;

import java.util.List;

public final class EducationCompletionControl {

    private final EducationRepository repository;

    public EducationCompletionControl(EducationRepository repository) {
        this.repository = repository;
    }

    public boolean registerCompletionResult(EducationCompletionResult result) {
        if (!validateCompletionResult(result) || !checkOpeningAvailable(result.openingId())) {
            return false;
        }
        repository.saveCompletionResult(result);
        return true;
    }

    public List<EducationCompletionResult> searchCompletionHistory(String keyword) {
        return repository.findCompletionResults(keyword);
    }

    public EducationCompletionResult getCompletionResultDetail(String completionResultId) {
        return repository.findCompletionResultById(completionResultId);
    }

    public boolean checkDuplicateCompletion(EducationCompletionResult result) {
        return repository.existsCompletionFor(result.researcherId(), result.openingId());
    }

    public boolean validateCompletionResult(EducationCompletionResult result) {
        return !result.completionResultId().isBlank()
                && !result.researcherId().isBlank()
                && !result.openingId().isBlank()
                && result.recognizedHours() >= 0
                && !result.completionStatus().isBlank();
    }

    public boolean checkOpeningAvailable(String openingId) {
        EducationOpening opening = repository.findOpeningById(openingId);
        return opening != null && !"취소".equals(opening.openingStatus());
    }

    public int getRecognizedHours(String openingId) {
        EducationOpening opening = repository.findOpeningById(openingId);
        return opening == null ? 0 : opening.recognizedHours();
    }
}
