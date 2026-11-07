package xyz.devcham.farmap.farm.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RequestCreateFarm(
        @NotBlank @Length(min = 8, max = 8) String userId,
        @NotBlank @Length(min = 4, max = 50) String name,
        @NotBlank @Length(min = 4, max = 50) String location,
        @Pattern(regexp = "^#[0-9a-f]{6}$") String colorString) {
}
