package xyz.devcham.farmap.farmPoint;

public class FarmPoint {
    private String id;
    private String farmId;
    private double latitude;
    private double longitude;
    private int pointOrder;

    public FarmPoint(String id, String farmId, double latitude, double longitude, int pointOrder) {
        this.id = id;
        this.farmId = farmId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pointOrder = pointOrder;
    }

    public String getId() {
        return id;
    }

    public String getFarmId() {
        return farmId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getPointOrder() {
        return pointOrder;
    }
}
