package xyz.devcham.farmap.user;

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
}
