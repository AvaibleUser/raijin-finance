package edu.raijin.finance.finance.infrastructure.adapter.in.rest.dto.payroll;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record PayrollDto(
        Long id,
        UUID employeeId,
        BigDecimal baseSalary,
        BigDecimal totalBonuses,
        BigDecimal totalDiscounts,
        BigDecimal totalPaid,
        LocalDate paymentDate,
        LocalDate fromDate,
        LocalDate toDate,
        Instant createdAt,
        Instant updatedAt) {
}
