package xyz.devcham.farmap.farm;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import xyz.devcham.farmap.utils.Regexp;

public record RequestCreateFarm(
        @NotNull @Pattern(regexp = Regexp.ID) @Length(min = 8, max = 8) String userId,
        @NotNull @Length(min = 4, max = 50) String name,
        @NotNull @Length(min = 4, max = 50) String location,
        @Pattern(regexp = "^#[0-9a-f]{6}$") String colorString,
        @NotNull int farmType) {
}
