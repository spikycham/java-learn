package xyz.devcham.farmap.farmPoint;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.devcham.farmap.utils.Random;

@Service
public class FarmPointService {
    private final FarmPointRepository repo;

    public FarmPointService(FarmPointRepository repo) {
        this.repo = repo;
    }

    public List<FarmPoint> createFarmPoints(RequestUpdateFarmPoints req) {
        List<FarmPoint> points = req.points().stream().map(p -> new FarmPoint(
                Random.generateId(8),
                req.farmId(),
                p.latitude(),
                p.longitude(),
                p.pointOrder())).toList();
        repo.insertFarmPoints(points);
        // TODO: update the related farm area.
        return points;
    }

    public List<FarmPoint> readFarmPoints(String farmId) {
        return repo.selectAllByFarmId(farmId);
    }

    // PERF: clarify update coordinates or reorder the points.
    @Transactional
    public List<FarmPoint> updateFarmPoints(RequestUpdateFarmPoints req) {
        // TODO: update the related farm area.
        repo.deleteAllByFarmId(req.farmId());
        return createFarmPoints(req);
    }
}
