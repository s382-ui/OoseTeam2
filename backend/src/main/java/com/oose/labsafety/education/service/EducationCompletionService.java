package com.oose.labsafety.education.service;

import com.oose.labsafety.education.domain.EducationCompletionResult;
import com.oose.labsafety.education.domain.EducationCourse;
import com.oose.labsafety.education.domain.EducationOpening;
import com.oose.labsafety.education.infrastructure.EducationRepository;
import com.oose.labsafety.user.domain.User;
import com.oose.labsafety.user.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationCompletionService {
    private final EducationRepository educationRepository;
    private final UserRepository userRepository;

    public EducationCompletionService(
            EducationRepository educationRepository,
            UserRepository userRepository) {
        this.educationRepository = educationRepository;
        this.userRepository = userRepository;
    }

    public EducationCompletionResult register(EducationCompletionResult result) {
        if (educationRepository.completionResults().findById(result.completionResultId()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 이수결과 ID입니다: " + result.completionResultId());
        }
        if (userRepository.findById(result.researcherId()).isEmpty()) {
            throw new IllegalArgumentException("등록되지 않은 연구활동종사자 ID입니다: " + result.researcherId());
        }
        if (educationRepository.educationOpenings().findById(result.openingId()).isEmpty()) {
            throw new IllegalArgumentException("등록되지 않은 교육개설정보 ID입니다: " + result.openingId());
        }
        return educationRepository.completionResults().save(result);
    }

    public List<CompletionHistoryView> findAll() {
        return educationRepository.completionResults().findAll().stream()
                .map(result -> {
                    User user = userRepository.findById(result.researcherId()).orElse(null);
                    EducationOpening opening = educationRepository.educationOpenings()
                            .findById(result.openingId()).orElse(null);
                    EducationCourse course = opening == null ? null
                            : educationRepository.educationCourses()
                            .findById(opening.courseId()).orElse(null);

                    return new CompletionHistoryView(
                            result.completionResultId(),
                            result.researcherId(),
                            user == null ? "미등록 사용자" : user.userName(),
                            result.openingId(),
                            course == null ? "미등록 과정" : course.courseName(),
                            course == null ? "" : course.educationType(),
                            result.completionDate(),
                            result.recognizedHours(),
                            result.completionStatus(),
                            result.manualRegistration());
                })
                .toList();
    }

    public record CompletionHistoryView(
            String completionResultId,
            String researcherId,
            String researcherName,
            String openingId,
            String courseName,
            String educationType,
            String completionDate,
            int recognizedHours,
            String completionStatus,
            boolean manualRegistration
    ) {
    }
}
