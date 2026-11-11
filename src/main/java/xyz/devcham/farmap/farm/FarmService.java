package xyz.devcham.farmap.farm;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import xyz.devcham.farmap.utils.Random;

@Service
public class FarmService {
    private final FarmRepository repo;

    public FarmService(FarmRepository repo) {
        this.repo = repo;
    }

    public Farm createFarm(RequestCreateFarm req) {
        Farm farm = new Farm(
                Random.generateId(8),
                req.userId(),
                req.name(),
                req.location(),
                Instant.now(),
                req.colorString(),
                0.0F,
                FarmType.fromValue(req.farmType()));
        repo.insertFarm(farm);
        return farm;
    }

    public List<Farm> readFarmList(String userId) {
        return repo.selectAllByUserId(userId);
    }

    public void updateFarm(RequestUpdateFarm req) {
        Map<String, Object> fields = new HashMap<>();

        if (req.name() != null)
            fields.put("name", req.name());
        if (req.location() != null)
            fields.put("location", req.location());
        if (req.colorString() != null)
            fields.put("color_string", req.colorString());
        if (req.farmType() != null)
            fields.put("farm_type", req.farmType());

        if (fields.isEmpty())
            return;
        repo.updateFarmByFarmId(
                req.farmId(),
                fields);
    }

    public void deleteFarm(String farmId) {
        repo.deleteFarmByFarmId(farmId);
        // TODO: delete related farm points.
    }
}
