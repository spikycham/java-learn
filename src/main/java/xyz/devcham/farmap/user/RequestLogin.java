package xyz.devcham.farmap.user;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RequestLogin(
        @Email @NotNull @Length(min = 4, max = 50) String email,
        @NotNull @Length(min = 4, max = 50) String password) {
}
