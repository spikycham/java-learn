package xyz.devcham.farmap.farm;

import java.time.Instant;

public class Farm {
    private String id;
    private String userId;
    private String name;
    private String location;
    private Instant createdAt;
    private String color_string;

    public Farm(String id, String userId, String name, String location, Instant createdAt, String color_string) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.location = location;
        this.createdAt = createdAt;
        this.color_string = color_string;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getColorString() {
        return color_string;
    }
}
