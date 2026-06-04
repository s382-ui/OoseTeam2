package com.oose.labsafety.waste.infrastructure;

import com.oose.labsafety.common.Database;
import com.oose.labsafety.common.JdbcCrudRepository;
import com.oose.labsafety.waste.domain.WasteRecord;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public final class WasteRepository extends JdbcCrudRepository<WasteRecord> {

    public WasteRepository() {
        super("Waste", "wasteId");
    }

    @Override
    public void save(WasteRecord item) {
        String sql = """
                INSERT INTO Waste
                    (wasteId, labId, wasteType, quantity, unit, disposalDate, status, handlerId, createdAt)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE
                    labId = VALUES(labId),
                    wasteType = VALUES(wasteType),
                    quantity = VALUES(quantity),
                    unit = VALUES(unit),
                    disposalDate = VALUES(disposalDate),
                    status = VALUES(status),
                    handlerId = VALUES(handlerId),
                    createdAt = VALUES(createdAt)
                """;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.wasteId());
            statement.setString(2, item.labId());
            statement.setString(3, item.wasteType());
            statement.setBigDecimal(4, item.quantity());
            statement.setString(5, item.unit());
            statement.setDate(6, Date.valueOf(item.disposalDate()));
            statement.setString(7, item.status());
            statement.setString(8, item.handlerId());
            statement.setTimestamp(9, Timestamp.valueOf(item.createdAt()));
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new IllegalStateException("폐기물 저장 중 DB 오류가 발생했습니다.", exception);
        }
    }

    @Override
    protected List<WasteRecord> mapAll(ResultSet resultSet) throws SQLException {
        List<WasteRecord> wastes = new ArrayList<>();
        while (resultSet.next()) {
            wastes.add(mapRow(resultSet));
        }
        return wastes;
    }

    @Override
    protected WasteRecord mapRow(ResultSet resultSet) throws SQLException {
        return new WasteRecord(
                resultSet.getString("wasteId"),
                resultSet.getString("labId"),
                resultSet.getString("wasteType"),
                resultSet.getBigDecimal("quantity"),
                resultSet.getString("unit"),
                resultSet.getDate("disposalDate").toLocalDate(),
                resultSet.getString("status"),
                resultSet.getString("handlerId"),
                resultSet.getTimestamp("createdAt").toLocalDateTime()
        );
    }
}
