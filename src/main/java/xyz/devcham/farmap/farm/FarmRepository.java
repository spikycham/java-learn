package xyz.devcham.farmap.farm;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FarmRepository {
    private final JdbcTemplate jdbc;

    public FarmRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Farm> getAllByUserId(String userId) {
        return jdbc.query(
                "SELECT id, user_id, name, location, created_at, color_string FROM farms WHERE user_id = ?",
                (rs, rowNum) -> new Farm(
                        rs.getString("id"),
                        rs.getString("user_id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getTimestamp("created_at").toInstant(),
                        rs.getString("color_string")),
                userId);
    }
}
