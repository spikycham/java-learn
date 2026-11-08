package xyz.devcham.farmap.crop;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import xyz.devcham.farmap.utils.Regexp;

@RestController
@RequestMapping("/api/crop")
public class CropController {
    private final CropService svc;

    public CropController(CropService svc) {
        this.svc = svc;
    }

    @PostMapping("")
    public ResponseEntity<Void> postCrops(@Valid @RequestBody RequestCreateCrops req) throws SQLException {
        if (req.crops().size() == 0)
            return ResponseEntity.badRequest().build();
        svc.createCrops(req);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/{farm_id}/{date}")
    public ResponseEntity<List<Crop>> getCrops(@PathVariable("farm_id") String farmId,
            @PathVariable("date") long date) {
        if (farmId.length() != 8 || !farmId.matches(Regexp.ID))
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(svc.readCrops(farmId, Instant.ofEpochSecond(date)));
    }

    @DeleteMapping("/{farm_id}/{date}")
    public ResponseEntity<Void> deleteCrops(@PathVariable("farm_id") String farmId, @PathVariable("date") long date) {
        if (farmId.length() != 8 || !farmId.matches(Regexp.ID))
            return ResponseEntity.badRequest().build();
        svc.deleteCrops(farmId, Instant.ofEpochSecond(date));
        return ResponseEntity.noContent().build();
    }
}
