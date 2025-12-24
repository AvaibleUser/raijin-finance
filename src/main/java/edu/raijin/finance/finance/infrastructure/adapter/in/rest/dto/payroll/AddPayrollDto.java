package edu.raijin.finance.finance.infrastructure.adapter.in.rest.dto.payroll;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record AddPayrollDto(
        @NotNull(message = "El salario base es requerido") @Positive(message = "El salario base debe ser mayor a cero") BigDecimal baseSalary,
        @PositiveOrZero(message = "Los bonos deben ser mayores o iguales a cero") BigDecimal totalBonuses,
        @PositiveOrZero(message = "Los descuentos deben ser mayores o iguales a cero") BigDecimal totalDiscounts,
        @JsonFormat(pattern = "yyyy-MM-dd") @NotNull(message = "La fecha de pago es requerida") LocalDate paymentDate,
        @JsonFormat(pattern = "yyyy-MM-dd") @NotNull(message = "La fecha desde la cual se paga es requerida") LocalDate fromDate,
        @JsonFormat(pattern = "yyyy-MM-dd") @NotNull(message = "La fecha hasta la cual se paga es requerida") LocalDate toDate) {
}
