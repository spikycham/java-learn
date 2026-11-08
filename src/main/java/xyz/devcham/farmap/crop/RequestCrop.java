package xyz.devcham.farmap.crop;

import jakarta.validation.constraints.NotNull;

public record RequestCrop(
                @NotNull String farmId,
                @NotNull int cropType,
                @NotNull String[] diseaseDetails,
                @NotNull int yieldCount,
                @NotNull float height) {

}
