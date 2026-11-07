package xyz.devcham.farmap.crop;

public class Crop {
    private String id;
    private String farmId;
    private int cropType;
    private String[] diseaseDetails;
    private int yieldCount;
    private float height;

    public Crop(String id, String farmId, int cropType, String[] diseaseDetails, int yieldCount, float height) {
        this.id = id;
        this.farmId = farmId;
        this.cropType = cropType;
        this.diseaseDetails = diseaseDetails;
        this.yieldCount = yieldCount;
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public String getFarmId() {
        return farmId;
    }

    public int getCropType() {
        return cropType;
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
}
