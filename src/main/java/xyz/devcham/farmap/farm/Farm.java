package xyz.devcham.farmap.farm;

import java.time.Instant;

public class Farm {
    private String id;
    private String userId;
    private String name;
    private String location;
    private Instant createdAt;

    public Farm(String id, String userId, String name, String location, Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.location = location;
        this.createdAt = createdAt;
    }
}
