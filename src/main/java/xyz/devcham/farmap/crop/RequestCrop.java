package xyz.devcham.farmap.crop;

import jakarta.validation.constraints.NotNull;

public record RequestCrop(
                @NotNull int cropType,
                @NotNull double latitude,
                @NotNull double longitude,
                @NotNull String[] diseaseDetails,
                @NotNull int yieldCount,
                @NotNull float height) {

}
