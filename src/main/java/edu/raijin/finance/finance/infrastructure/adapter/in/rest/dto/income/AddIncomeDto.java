package edu.raijin.finance.finance.infrastructure.adapter.in.rest.dto.income;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import edu.raijin.commons.domain.type.IncomeType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AddIncomeDto(
        @Positive(message = "El monto del ingreso debe ser mayor a cero") BigDecimal amount,
        @NotNull(message = "El tipo de ingreso es requerido") IncomeType type,
        String description,
        @JsonFormat(pattern = "yyyy-MM-dd") @NotNull(message = "La fecha de facturación es requerida") LocalDate billingDate) {
}
