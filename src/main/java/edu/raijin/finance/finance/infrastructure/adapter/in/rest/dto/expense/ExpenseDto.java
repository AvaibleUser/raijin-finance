package edu.raijin.finance.finance.infrastructure.adapter.in.rest.dto.expense;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import edu.raijin.commons.domain.type.ExpenseType;

public record ExpenseDto(
        Long id,
        UUID projectId,
        UUID employeeId,
        String description,
        BigDecimal amount,
        ExpenseType type,
        LocalDate expenseDate,
        Instant createdAt,
        Instant updatedAt) {
}
