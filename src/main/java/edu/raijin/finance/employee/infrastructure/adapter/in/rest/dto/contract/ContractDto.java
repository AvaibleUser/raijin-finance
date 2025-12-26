package edu.raijin.finance.employee.infrastructure.adapter.in.rest.dto.contract;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import edu.raijin.commons.domain.type.ContractStatus;

public record ContractDto(
        UUID id,
        BigDecimal baseSalary,
        String role,
        String startDate,
        String endDate,
        ContractStatus status,
        Instant createdAt,
        Instant updatedAt) {
}
