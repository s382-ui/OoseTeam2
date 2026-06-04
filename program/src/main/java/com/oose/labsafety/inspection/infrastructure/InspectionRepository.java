package com.oose.labsafety.inspection.infrastructure;

import com.oose.labsafety.common.Database;
import com.oose.labsafety.common.JdbcCrudRepository;
import com.oose.labsafety.inspection.domain.InspectionRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public final class InspectionRepository extends JdbcCrudRepository<InspectionRecord> {

    public InspectionRepository() {
        super("InspectionCategory", "inspectionCategoryId");
    }

    @Override
    public void save(InspectionRecord item) {
        String sql = """
                INSERT INTO InspectionCategory
                    (inspectionCategoryId, categoryName, description, isActive, createdAt)
                VALUES (?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE
                    categoryName = VALUES(categoryName),
                    description = VALUES(description),
                    isActive = VALUES(isActive),
                    createdAt = VALUES(createdAt)
                """;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.categoryCode());
            statement.setString(2, item.categoryName());
            statement.setString(3, item.categoryDetail());
            statement.setString(4, item.useYn() ? "Y" : "N");
            statement.setTimestamp(5, Timestamp.valueOf(item.createdAt()));
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new IllegalStateException("점검분야 저장 중 DB 오류가 발생했습니다.", exception);
        }
    }

    @Override
    protected List<InspectionRecord> mapAll(ResultSet resultSet) throws SQLException {
        List<InspectionRecord> inspections = new ArrayList<>();
        while (resultSet.next()) {
            inspections.add(mapRow(resultSet));
        }
        return inspections;
    }

    @Override
    protected InspectionRecord mapRow(ResultSet resultSet) throws SQLException {
        return new InspectionRecord(
                resultSet.getString("inspectionCategoryId"),
                resultSet.getString("categoryName"),
                resultSet.getString("description"),
                "Y".equalsIgnoreCase(resultSet.getString("isActive")),
                resultSet.getTimestamp("createdAt").toLocalDateTime()
        );
    }
}
