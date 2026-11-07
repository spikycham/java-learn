package xyz.devcham.farmap.farm;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RequestUpdateFarm(
        @NotBlank String farmId,
        @Length(min = 4, max = 50) String name,
        @Length(min = 4, max = 50) String location,
        @Pattern(regexp = "^#[0-9a-f]{6}$") String colorString) {
}
