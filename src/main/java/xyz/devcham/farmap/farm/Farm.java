package xyz.devcham.farmap.farm;

import java.time.Instant;

public class Farm {
    private String id;
    private String userId;
    private String name;
    private String location;
    private Instant createdAt;
    private String colorString;
    private Float area;
    private FarmType farmType;

    public Farm(String id, String userId, String name, String location, Instant createdAt, String colorString,
            Float area, FarmType farmType) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.location = location;
        this.createdAt = createdAt;
        this.colorString = colorString;
        this.area = area;
        this.farmType = farmType;
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
        return colorString;
    }

    public Float getArea() {
        return area;
    }

    public FarmType getFarmType() {
        return farmType;
    }
}
