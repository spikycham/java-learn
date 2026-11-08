package xyz.devcham.farmap.crop;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CropRepository {
    private final JdbcTemplate db;
    private final DataSource ds;

    public CropRepository(JdbcTemplate db, DataSource ds) {
        this.db = db;
        this.ds = ds;
    }

    public void insertCrops(List<Crop> crops) throws SQLException {
        String insertClause = crops.stream().map(c -> "(?, ?, ?, ?, ?, ?, ?)").collect(Collectors.joining(", "));
        String sql = "INSERT INTO crops (id, farm_id, latitude, longitude, disease_details, yield_count, height) VALUES "
                + insertClause;

        Connection conn = ds.getConnection();
        Object[] args = new Object[crops.size() * 7];
        for (int i = 0; i < crops.size(); i++) {
            Crop c = crops.get(i);
            args[i * 7 + 0] = c.getId();
            args[i * 7 + 1] = c.getFarmId();
            args[i * 7 + 2] = c.getLatitude();
            args[i * 7 + 3] = c.getLongitude();
            args[i * 7 + 4] = conn.createArrayOf("text", c.getDiseaseDetails());
            args[i * 7 + 5] = c.getYieldCount();
            args[i * 7 + 6] = c.getHeight();
        }

        db.update(sql, args);
    }

    public List<Crop> selectAllByFarmIdAndDate(String farmId, Instant date) {
        return db.query(
                "SELECT id, farm_id, latitude, longitude, disease_details, yield_count, height FROM crops WHERE farm_id = ? AND created_at >= ? AND created_at < ?::timestamptz + interval '1 day'",
                (rs, rowNum) -> {
                    Array arr = rs.getArray("disease_details");
                    String[] diseaseDetails = (String[]) arr.getArray();

                    return new Crop(
                            rs.getString("id"),
                            rs.getString("farm_id"),
                            rs.getDouble("latitude"),
                            rs.getDouble("longitude"),
                            diseaseDetails,
                            rs.getInt("yield_count"),
                            rs.getFloat("height"),
                            rs.getTimestamp("created_at").toInstant());
                },
                farmId, date, date);
    }

    public void deleteCropsByFarmIdAndDate(String farmId, Instant date) {
        db.update(
                "DELETE FROM crops WHERE farm_id = ? AND created_at >= ? AND created_at < ?::timestamptz + interval '1 day'",
                farmId, date, date);
    }
}
