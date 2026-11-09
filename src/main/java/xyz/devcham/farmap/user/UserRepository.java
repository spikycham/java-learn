package xyz.devcham.farmap.user;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final JdbcTemplate db;

    public UserRepository(JdbcTemplate db) {
        this.db = db;
    }

    public void insertUser(User user) {
        db.update(
                "INSERT INTO users (id, email, password, username) VALUES (?, ?, ?, ?)",
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getUsername());
    }

    public User getUserByEmailAndPassword(String email, String password) {
        List<User> rows = db.query(
                "SELECT id, username, password, email FROM users WHERE email = ? AND password = ?",
                (rs, rowNum) -> new User(
                        rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("username")),
                email, password);
        return rows.isEmpty() ? null : rows.get(0);
    }

    public User getUserById(String userId) {
        List<User> rows = db.query(
                "SELECT id, username, password, email FROM users WHERE id = ?",
                (rs, rowNum) -> new User(
                        rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("username")),
                userId);
        return rows.isEmpty() ? null : rows.get(0);
    }
}
