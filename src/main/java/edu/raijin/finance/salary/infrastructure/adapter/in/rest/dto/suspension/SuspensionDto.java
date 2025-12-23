package edu.raijin.finance.salary.infrastructure.adapter.in.rest.dto.suspension;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record SuspensionDto(
        Long id,
        BigDecimal amount,
        String reason,
        LocalDate startDate,
        LocalDate endDate,
        Instant createdAt,
        Instant updatedAt) {
}
