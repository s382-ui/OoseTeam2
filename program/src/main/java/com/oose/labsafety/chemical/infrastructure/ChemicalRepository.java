package com.oose.labsafety.chemical.infrastructure;

import com.oose.labsafety.chemical.domain.ChemicalItem;
import com.oose.labsafety.common.Database;
import com.oose.labsafety.common.JdbcCrudRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public final class ChemicalRepository extends JdbcCrudRepository<ChemicalItem> {

    public ChemicalRepository() {
        super("Chemical", "chemicalId");
    }

    @Override
    public void save(ChemicalItem item) {
        String sql = """
                INSERT INTO Chemical
                    (chemicalId, labId, chemicalName, casNumber, quantity, unit, hazardGrade, storageMethod, expiryDate, createdAt)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE
                    labId = VALUES(labId),
                    chemicalName = VALUES(chemicalName),
                    casNumber = VALUES(casNumber),
                    quantity = VALUES(quantity),
                    unit = VALUES(unit),
                    hazardGrade = VALUES(hazardGrade),
                    storageMethod = VALUES(storageMethod),
                    expiryDate = VALUES(expiryDate),
                    createdAt = VALUES(createdAt)
                """;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.chemicalId());
            statement.setString(2, item.labId());
            statement.setString(3, item.chemicalName());
            statement.setString(4, item.casNumber());
            statement.setBigDecimal(5, item.quantity());
            statement.setString(6, item.unit());
            statement.setString(7, item.hazardGrade());
            statement.setString(8, item.storageMethod());
            statement.setDate(9, Date.valueOf(item.expiryDate()));
            statement.setTimestamp(10, Timestamp.valueOf(item.createdAt()));
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new IllegalStateException("화학물질 저장 중 DB 오류가 발생했습니다.", exception);
        }
    }

    @Override
    protected List<ChemicalItem> mapAll(ResultSet resultSet) throws SQLException {
        List<ChemicalItem> chemicals = new ArrayList<>();
        while (resultSet.next()) {
            chemicals.add(mapRow(resultSet));
        }
        return chemicals;
    }

    @Override
    protected ChemicalItem mapRow(ResultSet resultSet) throws SQLException {
        return new ChemicalItem(
                resultSet.getString("chemicalId"),
                resultSet.getString("labId"),
                resultSet.getString("chemicalName"),
                resultSet.getString("casNumber"),
                resultSet.getBigDecimal("quantity"),
                resultSet.getString("unit"),
                resultSet.getString("hazardGrade"),
                resultSet.getString("storageMethod"),
                resultSet.getDate("expiryDate").toLocalDate(),
                resultSet.getTimestamp("createdAt").toLocalDateTime()
        );
    }
}
