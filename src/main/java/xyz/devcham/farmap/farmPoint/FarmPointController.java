package xyz.devcham.farmap.farmPoint;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/farm/point")
public class FarmPointController {
    private final FarmPointService svc;

    public FarmPointController(FarmPointService svc) {
        this.svc = svc;
    }

    @PostMapping("")
    public List<FarmPoint> postFarmPoints(RequestUpdateFarmPoints req) {
        return svc.createFarmPoints(req);
    }

    @GetMapping("/{farm_id}")
    public List<FarmPoint> getFarmPoints(@PathVariable("farm_id") String farmId) {
        return svc.readFarmPoints(farmId);
    }

    @PutMapping("")
    public ResponseEntity<Void> putFarmPoints(RequestUpdateFarmPoints req) {
        svc.updateFarmPoints(req);
        return ResponseEntity.ok(null);
    }
}
