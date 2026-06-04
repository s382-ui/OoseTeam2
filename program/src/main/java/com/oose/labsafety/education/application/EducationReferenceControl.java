package com.oose.labsafety.education.application;

import com.oose.labsafety.education.domain.EducationCourse;
import com.oose.labsafety.education.domain.EducationOpening;
import com.oose.labsafety.education.infrastructure.EducationRepository;

import java.util.List;

public final class EducationReferenceControl {

    private final EducationRepository repository;

    public EducationReferenceControl(EducationRepository repository) {
        this.repository = repository;
    }

    public List<EducationCourse> searchEducationCourse(String keyword) {
        return repository.findEducationCourses(keyword);
    }

    public List<EducationOpening> searchEducationOpening(String keyword) {
        return repository.findEducationOpenings(keyword);
    }

    public EducationCourse getCourseDetail(String courseId) {
        return searchEducationCourse(courseId).stream()
                .filter(course -> course.courseId().equals(courseId))
                .findFirst()
                .orElse(null);
    }

    public EducationOpening getOpeningDetail(String openingId) {
        return repository.findOpeningById(openingId);
    }

    public boolean checkOpeningStatus(String openingId) {
        EducationOpening opening = getOpeningDetail(openingId);
        return opening != null && !"취소".equals(opening.openingStatus());
    }
}
