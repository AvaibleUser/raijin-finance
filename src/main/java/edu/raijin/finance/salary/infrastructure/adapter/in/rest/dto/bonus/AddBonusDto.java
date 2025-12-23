package edu.raijin.finance.salary.infrastructure.adapter.in.rest.dto.bonus;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record AddBonusDto(
        @PositiveOrZero(message = "El salario a bonificar es requerido") BigDecimal amount,
        @NotBlank(message = "El motivo del bono es requerido") String reason,
        Instant createdAt) {
}
