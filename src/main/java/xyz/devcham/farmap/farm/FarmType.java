package xyz.devcham.farmap.farm;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FarmType {
    CITRUS(1),
    PLUM(2),
    RICE(3);

    FarmType(int value) {
        this.value = value;
    }

    private final int value;

    public int getValue() {
        return value;
    }

    public static FarmType fromValue(int value) {
        for (FarmType t : values()) {
            if (t.value == value)
                return t;
        }
        throw new IllegalArgumentException("unknown farm type: " + value);
    }

    @JsonValue
    public int value() {
        return value;
    }
}
