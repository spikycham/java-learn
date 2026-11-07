package xyz.devcham.farmap.farm;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FarmRepository {
    private final JdbcTemplate jdbc;

    public FarmRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void insertFarm(Farm farm) {
        jdbc.update(
                "INSERT INTO farms (id, user_id, name, location, created_at, color_string) VALUES (?, ?, ?, ?, ?, ?)",
                farm.getId(),
                farm.getUserId(),
                farm.getName(),
                farm.getLocation(),
                java.sql.Timestamp.from(farm.getCreatedAt()),
                farm.getColorString());
    }

    public List<Farm> getAllByUserId(String userId) {
        return jdbc.query(
                "SELECT id, user_id, name, location, created_at, color_string, area FROM farms WHERE user_id = ?",
                (rs, rowNum) -> new Farm(
                        rs.getString("id"),
                        rs.getString("user_id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getTimestamp("created_at").toInstant(),
                        rs.getString("color_string"),
                        rs.getFloat("area")),
                userId);
    }

    // public FarmDetail getFarmDetailByFarmId(String farmId) {}

    // Desicion#0001
    public void updateFarmByFarmId(String farmId, Map<String, String> fields) {
        if (fields.isEmpty())
            return;

        String setClause = fields.keySet().stream()
                .map(key -> key + " = ? ")
                .collect(Collectors.joining(", "));
        String sql = "UPDATE farms SET " + setClause + " WHERE id = ?";

        jdbc.update(
                sql,
                Stream.concat(fields.values().stream(), Stream.of(farmId)).toArray());
    }

    public void deleteFarmByFarmId(String farmId) {
        jdbc.update(
                "DELETE FROM farms WHERE id = ?",
                farmId);
    }
}
