package edu.raijin.finance.salary.infrastructure.adapter.in.rest.dto.discount;

import java.math.BigDecimal;
import java.time.Instant;

public record DiscountDto(
        Long id,
        BigDecimal amount,
        String reason,
        Instant createdAt,
        Instant updatedAt) {
}
