package xyz.devcham.farmap.farmPoint;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import xyz.devcham.farmap.utils.Regexp;

@RestController
@RequestMapping("/api/farm/point")
public class FarmPointController {
    private final FarmPointService svc;

    public FarmPointController(FarmPointService svc) {
        this.svc = svc;
    }

    @PostMapping("")
    public ResponseEntity<List<ResponseUpdateFarmPoints>> postFarmPoints(
            @Valid @RequestBody RequestUpdateFarmPoints req) {
        if (req.points().size() == 0)
            return ResponseEntity.badRequest().build();
        System.out.println(req);
        return ResponseEntity.created(null).body(svc.createFarmPoints(req));
    }

    @GetMapping("/{farm_id}")
    public ResponseEntity<List<FarmPoint>> getFarmPoints(@PathVariable("farm_id") String farmId) {
        if (farmId.length() != 8 || !farmId.matches(Regexp.ID))
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(
                svc.readFarmPoints(farmId));
    }

    @PutMapping("")
    public ResponseEntity<Void> putFarmPoints(@Valid @RequestBody RequestUpdateFarmPoints req) {
        svc.updateFarmPoints(req);
        return ResponseEntity.ok(null);
    }
}
