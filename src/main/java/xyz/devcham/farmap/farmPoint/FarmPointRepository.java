package xyz.devcham.farmap.farmPoint;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FarmPointRepository {
        private final JdbcTemplate db;

        public FarmPointRepository(JdbcTemplate db) {
                this.db = db;
        }

        public void insertFarmPoints(List<FarmPoint> points) {
                String insertClause = points.stream().map(p -> "(?, ?, ?, ?, ?)").collect(Collectors.joining(", "));
                String sql = "INSERT INTO farm_points (id, farm_id, latitude, longitude, point_order) VALUES "
                                + insertClause;
                db.update(
                                sql,
                                points.stream().flatMap(
                                                p -> Stream.of(
                                                                p.getId(),
                                                                p.getFarmId(),
                                                                p.getLatitude(), p.getLongitude(),
                                                                p.getPointOrder()))
                                                .toArray());
        }

        public List<FarmPoint> selectAllByFarmId(String farmId) {
                return db.query(
                                "SELECT id, farm_id, latitude, longitude, point_order FROM farm_points WHERE farm_id = ? ORDER BY point_order",
                                (rs, rowNum) -> new FarmPoint(
                                                rs.getString("id"),
                                                rs.getString("farm_id"),
                                                rs.getDouble("latitude"),
                                                rs.getDouble("longitude"),
                                                rs.getInt("point_order")),
                                farmId);
        }

        // Update means to call delete and insert in a transaction.

        public void deleteAllByFarmId(String farmId) {
                db.update(
                                "DELETE FROM farm_points WHERE farm_id = ?",
                                farmId);
        }
}
