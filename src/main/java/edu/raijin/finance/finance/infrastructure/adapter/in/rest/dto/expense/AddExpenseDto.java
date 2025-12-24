package edu.raijin.finance.finance.infrastructure.adapter.in.rest.dto.expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import edu.raijin.commons.domain.type.ExpenseType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AddExpenseDto(
        UUID employeeId,
        @NotNull(message = "La descripción es requerida") String description,
        @Positive(message = "El monto del gasto debe ser mayor a cero") BigDecimal amount,
        @NotNull(message = "El tipo de gasto es requerido") ExpenseType type,
        @JsonFormat(pattern = "yyyy-MM-dd") @NotNull(message = "La fecha del gasto es requerida") LocalDate expenseDate) {
}
