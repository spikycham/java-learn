package xyz.devcham.farmap.farm;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/farm")
public class FarmController {
    private final FarmService svc;

    public FarmController(FarmService svc) {
        this.svc = svc;
    }

    @GetMapping("/list/{user_id}")
    public List<Farm> getList(@PathVariable String user_id) {
        return this.svc.listFarms(user_id);
    }
}
