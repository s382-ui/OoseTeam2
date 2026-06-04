package com.oose.labsafety.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class JdbcCrudRepository<T extends Identifiable> implements CrudRepository<T> {

    private final String findAllSql;
    private final String findByIdSql;
    private final String deleteByIdSql;
    private final String clearSql;
    private final String countSql;

    protected JdbcCrudRepository(String tableName, String idColumn) {
        this.findAllSql = "SELECT * FROM " + tableName + " ORDER BY " + idColumn;
        this.findByIdSql = "SELECT * FROM " + tableName + " WHERE " + idColumn + " = ?";
        this.deleteByIdSql = "DELETE FROM " + tableName + " WHERE " + idColumn + " = ?";
        this.clearSql = "DELETE FROM " + tableName;
        this.countSql = "SELECT COUNT(*) FROM " + tableName;
    }

    @Override
    public void saveAll(Collection<T> items) {
        for (T item : items) {
            save(item);
        }
    }

    @Override
    public List<T> findAll() {
        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(findAllSql)) {
            return mapAll(resultSet);
        } catch (SQLException exception) {
            throw new IllegalStateException("목록 조회 중 DB 오류가 발생했습니다.", exception);
        }
    }

    @Override
    public Optional<T> findById(String id) {
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(findByIdSql)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
                return Optional.empty();
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("상세 조회 중 DB 오류가 발생했습니다.", exception);
        }
    }

    @Override
    public boolean deleteById(String id) {
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteByIdSql)) {
            statement.setString(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException exception) {
            throw new IllegalStateException("삭제 중 DB 오류가 발생했습니다.", exception);
        }
    }

    @Override
    public void clear() {
        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearSql);
        } catch (SQLException exception) {
            throw new IllegalStateException("전체 삭제 중 DB 오류가 발생했습니다.", exception);
        }
    }

    @Override
    public int size() {
        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(countSql)) {
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException exception) {
            throw new IllegalStateException("건수 조회 중 DB 오류가 발생했습니다.", exception);
        }
    }

    protected abstract List<T> mapAll(ResultSet resultSet) throws SQLException;

    protected abstract T mapRow(ResultSet resultSet) throws SQLException;
}
