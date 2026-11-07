package xyz.devcham.farmap.farm;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import xyz.devcham.farmap.farm.dto.RequestCreateFarm;
import xyz.devcham.farmap.farm.dto.RequestUpdateFarm;

@RestController
@RequestMapping("/api/farm")
public class FarmController {
    private final FarmService svc;

    public FarmController(FarmService svc) {
        this.svc = svc;
    }

    @PostMapping("")
    public ResponseEntity<Farm> createFarm(@Valid @RequestBody RequestCreateFarm req) {
        Farm farm = svc.createFarm(req);
        return ResponseEntity.created(null).body(farm);
    }

    @GetMapping("/list/{user_id}")
    public ResponseEntity<List<Farm>> getFarmList(@PathVariable("user_id") String userId) {
        System.out.println(userId);
        if (userId.length() != 8 || !userId.matches("^[0-9a-f]{8}$"))
            return ResponseEntity.status(400).build();

        List<Farm> farms = svc.readFarmList(userId);
        return ResponseEntity.ok(farms);
    }

    @PatchMapping("")
    public ResponseEntity<Void> updateFarm(@Valid @RequestBody RequestUpdateFarm req) {
        svc.updateFarm(req);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{farm_id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable("farm_id") String farmId) {
        svc.deleteFarm(farmId);
        return ResponseEntity.noContent().build();
    }
}
