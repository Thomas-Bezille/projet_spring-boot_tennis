package com.dyma.tennis;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record Player(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @PastOrPresent LocalDate birthDate,
        @Valid Rank rank
) {
}
