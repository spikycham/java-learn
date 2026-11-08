package xyz.devcham.farmap.farmPoint;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record RequestUpdateFarmPoints(
        @NotNull String farmId,
        List<@Valid RequestFarmPoint> points) {
}
