package edu.raijin.finance.salary.infrastructure.adapter.in.rest.dto.bonus;

import java.math.BigDecimal;
import java.time.Instant;

public record BonusDto(
        Long id,
        BigDecimal amount,
        String reason,
        Instant createdAt,
        Instant updatedAt) {
}
