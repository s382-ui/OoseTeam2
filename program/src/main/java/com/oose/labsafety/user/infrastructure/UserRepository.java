package com.oose.labsafety.user.infrastructure;

import com.oose.labsafety.common.Database;
import com.oose.labsafety.common.JdbcCrudRepository;
import com.oose.labsafety.user.domain.UserAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public final class UserRepository extends JdbcCrudRepository<UserAccount> {

    public UserRepository() {
        super("`User`", "userId");
    }

    @Override
    public void save(UserAccount item) {
        String sql = """
                INSERT INTO `User` (userId, userName, department, role, contact, email, accountStatus, registeredAt)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE
                    userName = VALUES(userName),
                    department = VALUES(department),
                    role = VALUES(role),
                    contact = VALUES(contact),
                    email = VALUES(email),
                    accountStatus = VALUES(accountStatus),
                    registeredAt = VALUES(registeredAt)
                """;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.userId());
            statement.setString(2, item.userName());
            statement.setString(3, item.department());
            statement.setString(4, item.role());
            statement.setString(5, item.contact());
            statement.setString(6, item.email());
            statement.setString(7, item.accountStatus());
            statement.setTimestamp(8, Timestamp.valueOf(item.registeredAt()));
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new IllegalStateException("사용자 저장 중 DB 오류가 발생했습니다.", exception);
        }
    }

    @Override
    protected List<UserAccount> mapAll(ResultSet resultSet) throws SQLException {
        List<UserAccount> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(mapRow(resultSet));
        }
        return users;
    }

    @Override
    protected UserAccount mapRow(ResultSet resultSet) throws SQLException {
        return new UserAccount(
                resultSet.getString("userId"),
                resultSet.getString("userName"),
                resultSet.getString("department"),
                resultSet.getString("role"),
                resultSet.getString("contact"),
                resultSet.getString("email"),
                resultSet.getString("accountStatus"),
                resultSet.getTimestamp("registeredAt").toLocalDateTime()
        );
    }
}
