package edu.raijin.finance.salary.infrastructure.adapter.in.rest.dto.discount;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record AddDiscountDto(
        @PositiveOrZero(message = "El salario a descontar es requerido") BigDecimal amount,
        @NotBlank(message = "El motivo del descuento es requerido") String reason,
        Instant createdAt) {
}
