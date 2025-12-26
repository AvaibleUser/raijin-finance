package edu.raijin.finance.employee.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonBlank;
import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static edu.raijin.commons.util.exception.Exceptions.requirePositive;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import edu.raijin.commons.domain.type.ContractStatus;
import edu.raijin.commons.util.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Data
@With
@Builder
@Setter(NONE)
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class Contract {

    private UUID id;

    private UUID employeeId;

    private BigDecimal baseSalary;

    private String role;

    private LocalDate startDate;

    private LocalDate endDate;

    @Builder.Default
    private ContractStatus status = ContractStatus.ACTIVE;

    @Builder.Default
    private Boolean deleted = false;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    public void checkValidRegistration() {
        requireNonBlank(role, () -> new BadRequestException("El rol es requerido"));
        requirePositive(baseSalary, () -> new BadRequestException("El salario base es requerido"));
        requireNonNull(startDate, () -> new BadRequestException("La fecha de inicio es requerida"));
    }

    public void updateFrom(Contract updated) {
        this.baseSalary = firstNonNull(updated.baseSalary, this.baseSalary);
        this.role = firstNonNull(updated.role, this.role);
        this.startDate = firstNonNull(updated.startDate, this.startDate);
        this.endDate = firstNonNull(updated.endDate, this.endDate);
        this.status = firstNonNull(updated.status, this.status);
    }

    public void delete() {
        this.deleted = true;
        this.deletedAt = Instant.now();
    }
}
