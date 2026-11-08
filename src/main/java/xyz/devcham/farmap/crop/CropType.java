package xyz.devcham.farmap.crop;

public enum CropType {
    CITRUS,
    PLUM,
    RICE;

    public static CropType fromValue(int value) {
        for (CropType t : values()) {
            if (t.ordinal() == value)
                return t;
        }
        throw new IllegalArgumentException("unknown crop type: " + value);
    }
}
