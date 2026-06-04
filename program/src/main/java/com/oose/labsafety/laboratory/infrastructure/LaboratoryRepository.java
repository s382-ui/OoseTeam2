package com.oose.labsafety.laboratory.infrastructure;

import com.oose.labsafety.common.Database;
import com.oose.labsafety.common.JdbcCrudRepository;
import com.oose.labsafety.laboratory.domain.LaboratoryProfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public final class LaboratoryRepository extends JdbcCrudRepository<LaboratoryProfile> {

    public LaboratoryRepository() {
        super("Laboratory", "labId");
    }

    @Override
    public void save(LaboratoryProfile item) {
        String sql = """
                INSERT INTO Laboratory
                    (labId, labName, buildingName, floor, roomNo, departmentName, managerId, managerName, contactNo, managementGrade, isActive, createdAt)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE
                    labName = VALUES(labName),
                    buildingName = VALUES(buildingName),
                    floor = VALUES(floor),
                    roomNo = VALUES(roomNo),
                    departmentName = VALUES(departmentName),
                    managerId = VALUES(managerId),
                    managerName = VALUES(managerName),
                    contactNo = VALUES(contactNo),
                    managementGrade = VALUES(managementGrade),
                    isActive = VALUES(isActive),
                    createdAt = VALUES(createdAt)
                """;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.labId());
            statement.setString(2, item.labName());
            statement.setString(3, item.buildingName());
            statement.setString(4, item.floor());
            statement.setString(5, item.roomNo());
            statement.setString(6, item.departmentName());
            statement.setString(7, item.managerId());
            statement.setString(8, item.managerName());
            statement.setString(9, item.contactNo());
            statement.setString(10, item.managementGrade());
            statement.setString(11, item.isActive());
            statement.setTimestamp(12, Timestamp.valueOf(item.createdAt()));
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new IllegalStateException("연구실 저장 중 DB 오류가 발생했습니다.", exception);
        }
    }

    @Override
    protected List<LaboratoryProfile> mapAll(ResultSet resultSet) throws SQLException {
        List<LaboratoryProfile> labs = new ArrayList<>();
        while (resultSet.next()) {
            labs.add(mapRow(resultSet));
        }
        return labs;
    }

    @Override
    protected LaboratoryProfile mapRow(ResultSet resultSet) throws SQLException {
        return new LaboratoryProfile(
                resultSet.getString("labId"),
                resultSet.getString("labName"),
                resultSet.getString("buildingName"),
                resultSet.getString("floor"),
                resultSet.getString("roomNo"),
                resultSet.getString("departmentName"),
                resultSet.getString("managerId"),
                resultSet.getString("managerName"),
                resultSet.getString("contactNo"),
                resultSet.getString("managementGrade"),
                resultSet.getString("isActive"),
                resultSet.getTimestamp("createdAt").toLocalDateTime()
        );
    }
}
