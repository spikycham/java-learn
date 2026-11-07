package xyz.devcham.farmap.farm;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FarmService {
    private final FarmRepository repo;

    public FarmService(FarmRepository repo) {
        this.repo = repo;
    }

    public List<Farm> listFarms(String userId) {
        return repo.getAllByUserId(userId);
    }

}
