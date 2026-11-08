package xyz.devcham.farmap.crop;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import xyz.devcham.farmap.utils.Random;

@Service
public class CropService {
    private final CropRepository repo;

    public CropService(CropRepository repo) {
        this.repo = repo;
    }

    public void createCrops(RequestCreateCrops req) throws SQLException {
        repo.insertCrops(req.crops().stream().map(c -> new Crop(
                Random.generateId(8),
                req.farmId(),
                CropType.fromValue(c.cropType()),
                c.latitude(),
                c.longitude(),
                c.diseaseDetails(),
                c.yieldCount(),
                c.height(),
                Instant.now())).toList());
    }

    public List<Crop> readCrops(String farmId, Instant date) {
        return repo.selectAllByFarmIdAndDate(farmId, date);
    }

    public void deleteCrops(String farmId, Instant date) {
        repo.deleteCropsByFarmIdAndDate(farmId, date);
    }
}
