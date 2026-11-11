package xyz.devcham.farmap.farmPoint;

import jakarta.validation.constraints.NotNull;

public record RequestFarmPoint(
        @NotNull double latitude,
        @NotNull double longitude,
        @NotNull int pointOrder) {
}
