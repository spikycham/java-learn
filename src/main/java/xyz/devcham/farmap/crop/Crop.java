package xyz.devcham.farmap.crop;

import java.time.Instant;

public class Crop {
    private String id;
    private String farmId;
    private CropType cropType;
    private double latitude;
    private double longitude
    private String[] diseaseDetails;
    private int yieldCount;
    private float height;
    private Instant createdAt;

    public Crop(String id, String farmId, CropType cropType, double latitude, double longitude, String[] diseaseDetails,
            int yieldCount, float height,
            Instant createdAt) {
        this.id = id;
        this.farmId = farmId;
        this.cropType = cropType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.diseaseDetails = diseaseDetails;
        this.yieldCount = yieldCount;
        this.height = height;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getFarmId() {
        return farmId;
    }

    public CropType getCropType() {
        return cropType;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String[] getDiseaseDetails() {
        return diseaseDetails;
    }

    public int getYieldCount() {
        return yieldCount;
    }

    public float getHeight() {
        return height;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
