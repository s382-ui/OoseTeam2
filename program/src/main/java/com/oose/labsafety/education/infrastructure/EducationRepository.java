package com.oose.labsafety.education.infrastructure;

import com.oose.labsafety.common.Database;
import com.oose.labsafety.common.JdbcCrudRepository;
import com.oose.labsafety.education.domain.CompletionStandard;
import com.oose.labsafety.education.domain.EducationCompletionResult;
import com.oose.labsafety.education.domain.EducationCourse;
import com.oose.labsafety.education.domain.EducationOpening;
import com.oose.labsafety.education.domain.ResearcherCategory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class EducationRepository extends JdbcCrudRepository<EducationCompletionResult> {

    public EducationRepository() {
        super("EducationCompletionResult", "completionResultId");
    }

    public void saveResearcherCategory(ResearcherCategory category) {
        validateCategoryNameOwner(category.researcherCategoryId(), category.categoryName());
        String sql = """
                INSERT INTO ResearcherCategory
                    (researcherCategoryId, categoryName, categoryDescription, isActive)
                VALUES (?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE
                    categoryName = VALUES(categoryName),
                    categoryDescription = VALUES(categoryDescription),
                    isActive = VALUES(isActive)
                """;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.researcherCategoryId());
            statement.setString(2, category.categoryName());
            statement.setString(3, category.categoryDescription());
            statement.setBoolean(4, category.active());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new IllegalStateException("연구활동종사자 분류 저장 중 DB 오류가 발생했습니다.", exception);
        }
    }

    public void saveResearcherCategory(String researcherCategoryId, String categoryName, String categoryDescription, boolean active) {
        saveResearcherCategory(new ResearcherCategory(researcherCategoryId, categoryName, categoryDescription, active));
    }

    public void saveCompletionStandard(CompletionStandard standard) {
        String sql = """
                INSERT INTO CompletionStandard
                    (standardId, researcherCategoryId, requiredHours, startDate, endDate)
                VALUES (?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE
                    researcherCategoryId = VALUES(researcherCategoryId),
                    requiredHours = VALUES(requiredHours),
                    startDate = VALUES(startDate),
                    endDate = VALUES(endDate)
                """;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, standard.standardId());
            statement.setString(2, standard.researcherCategoryId());
            statement.setInt(3, standard.requiredHours());
            statement.setDate(4, Date.valueOf(standard.startDate()));
            statement.setDate(5, Date.valueOf(standard.endDate()));
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new IllegalStateException("이수기준 저장 중 DB 오류가 발생했습니다.", exception);
        }
    }

    public void saveCompletionStandard(String standardId, String researcherCategoryId, int requiredHours, LocalDate startDate, LocalDate endDate) {
        saveCompletionStandard(new CompletionStandard(standardId, researcherCategoryId, requiredHours, startDate, endDate));
    }

    public List<ResearcherCategory> findResearcherCategories(String keyword) {
        String sql = """
                SELECT researcherCategoryId, categoryName, categoryDescription, isActive
                FROM ResearcherCategory
                WHERE ? = ''
                   OR researcherCategoryId LIKE CONCAT('%', ?, '%')
                   OR categoryName LIKE CONCAT('%', ?, '%')
                ORDER BY categoryName
                """;
        String safeKeyword = keyword == null ? "" : keyword.trim();
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int index = 1; index <= 3; index++) {
                statement.setString(index, safeKeyword);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                List<ResearcherCategory> categories = new ArrayList<>();
                while (resultSet.next()) {
                    categories.add(new ResearcherCategory(
                            resultSet.getString("researcherCategoryId"),
                            resultSet.getString("categoryName"),
                            resultSet.getString("categoryDescription"),
                            resultSet.getBoolean("isActive")
                    ));
                }
                return categories;
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("연구활동종사자 분류 조회 중 DB 오류가 발생했습니다.", exception);
        }
    }

    public List<CompletionStandard> findCompletionStandards(String keyword) {
        String sql = """
                SELECT standardId, researcherCategoryId, requiredHours, startDate, endDate
                FROM CompletionStandard
                WHERE ? = ''
                   OR standardId LIKE CONCAT('%', ?, '%')
                   OR researcherCategoryId LIKE CONCAT('%', ?, '%')
                ORDER BY startDate DESC, standardId
                """;
        String safeKeyword = keyword == null ? "" : keyword.trim();
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int index = 1; index <= 3; index++) {
                statement.setString(index, safeKeyword);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                List<CompletionStandard> standards = new ArrayList<>();
                while (resultSet.next()) {
                    standards.add(new CompletionStandard(
                            resultSet.getString("standardId"),
                            resultSet.getString("researcherCategoryId"),
                            resultSet.getInt("requiredHours"),
                            resultSet.getDate("startDate").toLocalDate(),
                            resultSet.getDate("endDate").toLocalDate()
                    ));
                }
                return standards;
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("이수기준 조회 중 DB 오류가 발생했습니다.", exception);
        }
    }

    public List<String> findResearcherStandards() {
        String sql = """
                SELECT
                    RC.researcherCategoryId,
                    RC.categoryName,
                    RC.categoryDescription,
                    RC.isActive,
                    CS.standardId,
                    CS.requiredHours,
                    CS.startDate,
                    CS.endDate
                FROM ResearcherCategory RC
                LEFT JOIN CompletionStandard CS
                    ON RC.researcherCategoryId = CS.researcherCategoryId
                ORDER BY RC.categoryName, CS.startDate DESC
                """;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<String> rows = new ArrayList<>();
            while (resultSet.next()) {
                rows.add("분류ID=%s, 분류명=%s, 설명=%s, 사용=%s, 이수기준ID=%s, 필수시간=%s, 적용기간=%s~%s".formatted(
                        resultSet.getString("researcherCategoryId"),
                        resultSet.getString("categoryName"),
                        resultSet.getString("categoryDescription"),
                        resultSet.getBoolean("isActive") ? "Y" : "N",
                        defaultText(resultSet.getString("standardId")),
                        defaultText(resultSet.getString("requiredHours")),
                        defaultText(resultSet.getString("startDate")),
                        defaultText(resultSet.getString("endDate"))
                ));
            }
            return rows;
        } catch (SQLException exception) {
            throw new IllegalStateException("연구활동종사자 및 이수기준 조회 중 DB 오류가 발생했습니다.", exception);
        }
    }

    public List<String> findCompletionHistories(String keyword) {
        String sql = """
                SELECT
                    ECR.completionResultId,
                    ECR.researcherId,
                    U.userName,
                    ECR.openingId,
                    EC.courseName,
                    EC.educationType,
                    ECR.completionDate,
                    ECR.recognizedHours,
                    ECR.completionStatus
                FROM EducationCompletionResult ECR
                LEFT JOIN `User` U
                    ON ECR.researcherId = U.userId
                LEFT JOIN EducationOpening EO
                    ON ECR.openingId = EO.openingId
                LEFT JOIN EducationCourse EC
                    ON EO.courseId = EC.courseId
                WHERE ? = ''
                   OR ECR.completionResultId LIKE CONCAT('%', ?, '%')
                   OR ECR.researcherId LIKE CONCAT('%', ?, '%')
                   OR U.userName LIKE CONCAT('%', ?, '%')
                   OR EC.courseName LIKE CONCAT('%', ?, '%')
                   OR ECR.completionStatus LIKE CONCAT('%', ?, '%')
                ORDER BY ECR.completionDate DESC, ECR.completionResultId
                """;
        String safeKeyword = keyword == null ? "" : keyword.trim();
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int index = 1; index <= 6; index++) {
                statement.setString(index, safeKeyword);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                List<String> rows = new ArrayList<>();
                while (resultSet.next()) {
                    rows.add("이수결과ID=%s, 연구활동종사자=%s(%s), 개설ID=%s, 과정=%s, 유형=%s, 이수일=%s, 인정시간=%d, 상태=%s".formatted(
                            resultSet.getString("completionResultId"),
                            resultSet.getString("researcherId"),
                            defaultText(resultSet.getString("userName")),
                            resultSet.getString("openingId"),
                            defaultText(resultSet.getString("courseName")),
                            defaultText(resultSet.getString("educationType")),
                            defaultText(resultSet.getString("completionDate")),
                            resultSet.getInt("recognizedHours"),
                            resultSet.getString("completionStatus")
                    ));
                }
                return rows;
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("교육이수 내역 조회 중 DB 오류가 발생했습니다.", exception);
        }
    }

    public List<EducationCompletionResult> findCompletionResults(String keyword) {
        String sql = """
                SELECT completionResultId, researcherId, openingId, learningResultId, logId, completionDate, recognizedHours, completionStatus
                FROM EducationCompletionResult
                WHERE ? = ''
                   OR completionResultId LIKE CONCAT('%', ?, '%')
                   OR researcherId LIKE CONCAT('%', ?, '%')
                   OR openingId LIKE CONCAT('%', ?, '%')
                   OR completionStatus LIKE CONCAT('%', ?, '%')
                ORDER BY completionDate DESC, completionResultId
                """;
        String safeKeyword = keyword == null ? "" : keyword.trim();
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int index = 1; index <= 5; index++) {
                statement.setString(index, safeKeyword);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                List<EducationCompletionResult> results = new ArrayList<>();
                while (resultSet.next()) {
                    Date completionDate = resultSet.getDate("completionDate");
                    results.add(new EducationCompletionResult(
                            resultSet.getString("completionResultId"),
                            resultSet.getString("researcherId"),
                            resultSet.getString("openingId"),
                            defaultText(resultSet.getString("learningResultId")),
                            defaultText(resultSet.getString("logId")),
                            completionDate == null ? null : completionDate.toLocalDate(),
                            resultSet.getInt("recognizedHours"),
                            resultSet.getString("completionStatus")
                    ));
                }
                return results;
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("교육이수결과 조회 중 DB 오류가 발생했습니다.", exception);
        }
    }

    public EducationCompletionResult findCompletionResultById(String completionResultId) {
        return findCompletionResults(completionResultId).stream()
                .filter(result -> result.completionResultId().equals(completionResultId))
                .findFirst()
                .orElse(null);
    }

    public void saveCompletionResult(EducationCompletionResult item) {
        save(item);
    }

    public boolean existsCompletionFor(String researcherId, String openingId) {
        String sql = """
                SELECT COUNT(*) AS duplicateCount
                FROM EducationCompletionResult
                WHERE researcherId = ?
                  AND openingId = ?
                """;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, researcherId);
            statement.setString(2, openingId);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt("duplicateCount") > 0;
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("교육이수결과 중복 확인 중 DB 오류가 발생했습니다.", exception);
        }
    }

    public EducationOpening findOpeningById(String openingId) {
        String sql = """
                SELECT
                    EO.openingId,
                    EO.courseId,
                    EC.courseName,
                    EC.educationType,
                    EC.recognizedHours,
                    EO.educationStartDate,
                    EO.educationEndDate,
                    EO.openingStatus
                FROM EducationOpening EO
                LEFT JOIN EducationCourse EC
                    ON EO.courseId = EC.courseId
                WHERE EO.openingId = ?
                """;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, openingId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return mapOpening(resultSet);
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("교육개설정보 조회 중 DB 오류가 발생했습니다.", exception);
        }
    }

    public List<EducationCourse> findEducationCourses(String keyword) {
        String sql = """
                SELECT courseId, courseName, educationType, recognizedHours
                FROM EducationCourse
                WHERE ? = ''
                   OR courseId LIKE CONCAT('%', ?, '%')
                   OR courseName LIKE CONCAT('%', ?, '%')
                   OR educationType LIKE CONCAT('%', ?, '%')
                ORDER BY courseName
                """;
        String safeKeyword = keyword == null ? "" : keyword.trim();
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int index = 1; index <= 4; index++) {
                statement.setString(index, safeKeyword);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                List<EducationCourse> courses = new ArrayList<>();
                while (resultSet.next()) {
                    courses.add(new EducationCourse(
                            resultSet.getString("courseId"),
                            resultSet.getString("courseName"),
                            resultSet.getString("educationType"),
                            resultSet.getInt("recognizedHours")
                    ));
                }
                return courses;
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("교육과정 조회 중 DB 오류가 발생했습니다.", exception);
        }
    }

    public List<EducationOpening> findEducationOpenings(String keyword) {
        String sql = """
                SELECT
                    EO.openingId,
                    EO.courseId,
                    EC.courseName,
                    EC.educationType,
                    EC.recognizedHours,
                    EO.educationStartDate,
                    EO.educationEndDate,
                    EO.openingStatus
                FROM EducationOpening EO
                LEFT JOIN EducationCourse EC
                    ON EO.courseId = EC.courseId
                WHERE ? = ''
                   OR EO.openingId LIKE CONCAT('%', ?, '%')
                   OR EC.courseName LIKE CONCAT('%', ?, '%')
                   OR EO.openingStatus LIKE CONCAT('%', ?, '%')
                ORDER BY EO.educationStartDate DESC
                """;
        String safeKeyword = keyword == null ? "" : keyword.trim();
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int index = 1; index <= 4; index++) {
                statement.setString(index, safeKeyword);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                List<EducationOpening> openings = new ArrayList<>();
                while (resultSet.next()) {
                    openings.add(mapOpening(resultSet));
                }
                return openings;
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("교육개설정보 조회 중 DB 오류가 발생했습니다.", exception);
        }
    }

    public boolean existsResearcherCategoryByName(String categoryName) {
        String sql = "SELECT COUNT(*) FROM ResearcherCategory WHERE categoryName = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, categoryName);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("연구활동종사자 분류 중복 확인 중 DB 오류가 발생했습니다.", exception);
        }
    }

    private void validateCategoryNameOwner(String researcherCategoryId, String categoryName) {
        String sql = """
                SELECT researcherCategoryId
                FROM ResearcherCategory
                WHERE categoryName = ?
                  AND researcherCategoryId <> ?
                """;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, categoryName);
            statement.setString(2, researcherCategoryId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    throw new IllegalStateException("이미 '%s' 분류명이 %s ID로 등록되어 있습니다. 기존 ID를 사용하거나 다른 분류명을 입력해 주세요."
                            .formatted(categoryName, resultSet.getString("researcherCategoryId")));
                }
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("연구활동종사자 분류 중복 확인 중 DB 오류가 발생했습니다.", exception);
        }
    }

    private EducationOpening mapOpening(ResultSet resultSet) throws SQLException {
        return new EducationOpening(
                resultSet.getString("openingId"),
                resultSet.getString("courseId"),
                defaultText(resultSet.getString("courseName")),
                defaultText(resultSet.getString("educationType")),
                resultSet.getInt("recognizedHours"),
                resultSet.getDate("educationStartDate").toLocalDate(),
                resultSet.getDate("educationEndDate").toLocalDate(),
                resultSet.getString("openingStatus")
        );
    }

    @Override
    public void save(EducationCompletionResult item) {
        String sql = """
                INSERT INTO EducationCompletionResult
                    (completionResultId, researcherId, openingId, learningResultId, logId, completionDate, recognizedHours, completionStatus)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE
                    researcherId = VALUES(researcherId),
                    openingId = VALUES(openingId),
                    learningResultId = VALUES(learningResultId),
                    logId = VALUES(logId),
                    completionDate = VALUES(completionDate),
                    recognizedHours = VALUES(recognizedHours),
                    completionStatus = VALUES(completionStatus)
                """;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.completionResultId());
            statement.setString(2, item.researcherId());
            statement.setString(3, item.openingId());
            statement.setString(4, nullableText(item.learningResultId()));
            statement.setString(5, nullableText(item.logId()));
            statement.setDate(6, item.completionDate() == null ? null : Date.valueOf(item.completionDate()));
            statement.setInt(7, item.recognizedHours());
            statement.setString(8, item.completionStatus());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new IllegalStateException("교육이수결과 저장 중 DB 오류가 발생했습니다.", exception);
        }
    }

    @Override
    protected List<EducationCompletionResult> mapAll(ResultSet resultSet) throws SQLException {
        List<EducationCompletionResult> records = new ArrayList<>();
        while (resultSet.next()) {
            records.add(mapRow(resultSet));
        }
        return records;
    }

    @Override
    protected EducationCompletionResult mapRow(ResultSet resultSet) throws SQLException {
        Date completionDate = resultSet.getDate("completionDate");
        return new EducationCompletionResult(
                resultSet.getString("completionResultId"),
                resultSet.getString("researcherId"),
                resultSet.getString("openingId"),
                defaultText(resultSet.getString("learningResultId")),
                defaultText(resultSet.getString("logId")),
                completionDate == null ? null : completionDate.toLocalDate(),
                resultSet.getInt("recognizedHours"),
                resultSet.getString("completionStatus")
        );
    }

    private String nullableText(String value) {
        if (value == null || value.isBlank() || "N/A".equalsIgnoreCase(value)) {
            return null;
        }
        return value;
    }

    private String defaultText(String value) {
        return value == null ? "N/A" : value;
    }
}
