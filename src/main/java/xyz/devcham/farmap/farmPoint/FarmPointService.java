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

    public List<ResponseUpdateFarmPoints> createFarmPoints(RequestUpdateFarmPoints req) {
        List<FarmPoint> points = req.points().stream().map(p -> new FarmPoint(
                Random.generateId(8),
                req.farmId(),
                p.latitude(),
                p.longitude(),
                p.pointOrder())).toList();
        repo.insertFarmPoints(points);
        // TODO: update the related farm area.
        return points.stream().map(p -> new ResponseUpdateFarmPoints(p.getId(), p.getPointOrder())).toList();
    }

    public List<FarmPoint> readFarmPoints(String farmId) {
        return repo.selectAllByFarmId(farmId);
    }

    // PERF: clarify update coordinates or reorder the points.
    @Transactional
    public void updateFarmPoints(RequestUpdateFarmPoints req) {
        // TODO: update the related farm area.
        repo.deleteAllByFarmId(req.farmId());
        createFarmPoints(req);
    }
}
