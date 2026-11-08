package xyz.devcham.farmap.farm;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FarmRepository {
    private final JdbcTemplate db;

    public FarmRepository(JdbcTemplate db) {
        this.db = db;
    }

    public void insertFarm(Farm farm) {
        db.update(
                "INSERT INTO farms (id, user_id, name, location, created_at, color_string, farm_type) VALUES (?, ?, ?, ?, ?, ?, ?)",
                farm.getId(),
                farm.getUserId(),
                farm.getName(),
                farm.getLocation(),
                java.sql.Timestamp.from(farm.getCreatedAt()),
                farm.getColorString(),
                farm.getFarmType().getValue());
    }

    public List<Farm> selectAllByUserId(String userId) {
        return db.query(
                "SELECT id, user_id, name, location, created_at, color_string, area, farm_type FROM farms WHERE user_id = ? ORDER BY created_at ASC",
                (rs, rowNum) -> new Farm(
                        rs.getString("id"),
                        rs.getString("user_id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getTimestamp("created_at").toInstant(),
                        rs.getString("color_string"),
                        rs.getFloat("area"),
                        FarmType.fromValue(rs.getInt("farm_type"))),
                userId);
    }

    // public FarmDetail getFarmDetailByFarmId(String farmId) {}

    // Desicion#0001
    public void updateFarmByFarmId(String farmId, Map<String, Object> fields) {
        if (fields.isEmpty())
            return;

        String setClause = fields.keySet().stream()
                .map(key -> key + " = ? ")
                .collect(Collectors.joining(", "));
        String sql = "UPDATE farms SET " + setClause + " WHERE id = ?";

        db.update(
                sql,
                Stream.concat(fields.values().stream(), Stream.of(farmId)).toArray());
    }

    // TODO: set a last_upload_at field to know the latest date of uploaindg a group
    // of crops.

    public void deleteFarmByFarmId(String farmId) {
        db.update(
                "DELETE FROM farms WHERE id = ?",
                farmId);
    }
}
