package edu.raijin.finance.salary.infrastructure.adapter.in.rest.dto.suspension;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record AddSuspensionDto(
        @PositiveOrZero(message = "El salario a suspender es requerido") BigDecimal amount,
        @NotBlank(message = "El motivo de la suspensión es requerido") String reason,
        @JsonFormat(pattern = "yyyy-MM-dd") @NotNull(message = "La fecha de inicio de la suspensión es requerida") LocalDate startDate,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
        Instant createdAt) {
}
