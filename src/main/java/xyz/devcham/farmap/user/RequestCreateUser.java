package xyz.devcham.farmap.user;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RequestCreateUser(
                @Email @NotNull @Length(min = 5, max = 50) String email,
                @NotNull @Length(min = 4, max = 50) String password,
                @NotNull @Length(min = 2, max = 50) @Pattern(regexp = "^[0-9a-zA-Z_ ]+$") String username) {
}
