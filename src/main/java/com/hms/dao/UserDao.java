package com.hms.dao;

import com.hms.model.User;
import java.sql.*;
import java.util.Optional;

public class UserDao {
    private final Connection connection;
    public UserDao(Connection connection) { this.connection = connection; }
    public Optional<User> findByUsername(String username) throws SQLException {
        String sql = "SELECT id, username, role FROM users WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(new User(rs.getInt("id"), rs.getString("username"), rs.getString("role"))) : Optional.empty();
            }
        }
    }
    public Optional<String> passwordHash(String username) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT password_hash FROM users WHERE username = ?")) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) { return rs.next() ? Optional.of(rs.getString(1)) : Optional.empty(); }
        }
    }
}
