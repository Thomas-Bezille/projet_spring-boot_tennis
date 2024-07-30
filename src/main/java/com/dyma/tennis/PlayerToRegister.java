package com.dyma.tennis;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record PlayerToRegister(
        @NotBlank(message = "First name is mandatory") String firstName,
        @NotBlank(message = "Last name is mandatory") String lastName,
        @NotNull(message = "Birth Date is mandatory") LocalDate birthDate,
        @PositiveOrZero(message = "Points nust be more than zero") int points
) {}
