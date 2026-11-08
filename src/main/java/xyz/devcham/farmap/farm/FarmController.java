package xyz.devcham.farmap.farm;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import xyz.devcham.farmap.utils.Regexp;

@RestController
@RequestMapping("/api/farm")
public class FarmController {
    private final FarmService svc;

    public FarmController(FarmService svc) {
        this.svc = svc;
    }

    // TODO: I think there should be a middleware to handle
    // the SQL injection for any text the user input.
    @PostMapping("")
    public ResponseEntity<Farm> postFarm(@Valid @RequestBody RequestCreateFarm req) {
        Farm farm = svc.createFarm(req);
        return ResponseEntity.created(null).body(farm);
    }

    @GetMapping("/list/{user_id}")
    public ResponseEntity<List<Farm>> getFarmList(@PathVariable("user_id") String userId) {
        List<Farm> farms = svc.readFarmList(userId);
        return ResponseEntity.ok(farms);
    }

    @PatchMapping("")
    public ResponseEntity<Void> patchFarm(@Valid @RequestBody RequestUpdateFarm req) {
        svc.updateFarm(req);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{farm_id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable("farm_id") String farmId) {
        if (farmId.length() != 8 || !farmId.matches(Regexp.ID))
            return ResponseEntity.status(400).build();

        svc.deleteFarm(farmId);
        return ResponseEntity.noContent().build();
    }
}
