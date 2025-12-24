package edu.raijin.finance.finance.infrastructure.adapter.in.rest.dto.income;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import edu.raijin.commons.domain.type.IncomeType;

public record IncomeDto(
        Long id,
        UUID projectId,
        BigDecimal amount,
        IncomeType type,
        String description,
        LocalDate billingDate,
        Instant createdAt,
        Instant updatedAt) {
}
