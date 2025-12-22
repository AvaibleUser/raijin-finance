package edu.raijin.finance.employee.infrastructure.adapter.in.rest.dto.contract;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import edu.raijin.commons.domain.type.ContractStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record AddContractDto(
        @PositiveOrZero(message = "El salario base debe ser mayor o igual a cero") BigDecimal baseSalary,
        @NotBlank(message = "El rol es requerido") String role,
        @JsonFormat(pattern = "yyyy-MM-dd") @NotNull(message = "La fecha de inicio del contrato es requerida") LocalDate startDate,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
        @NotNull(message = "El estado del contrato es requerido") ContractStatus status) {
}
