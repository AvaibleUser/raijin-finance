package edu.raijin.finance.employee.infrastructure.adapter.in.rest.dto.contract;

import java.math.BigDecimal;
import java.time.Instant;

import edu.raijin.commons.domain.type.ContractStatus;

public record ContractDto(
        Long id,
        BigDecimal baseSalary,
        String role,
        String startDate,
        String endDate,
        ContractStatus status,
        Instant createdAt,
        Instant updatedAt) {
}
