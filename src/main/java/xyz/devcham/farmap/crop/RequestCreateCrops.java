package xyz.devcham.farmap.crop;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record RequestCreateCrops(
        @NotNull String farmId,
        @NotNull List<@Valid RequestCrop> crops) {

}
