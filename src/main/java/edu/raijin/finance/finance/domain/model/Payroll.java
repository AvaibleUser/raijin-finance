package edu.raijin.finance.finance.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonNegative;
import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static edu.raijin.commons.util.exception.Exceptions.requirePositive;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import edu.raijin.commons.util.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Data
@With
@Builder(toBuilder = true)
@Setter(NONE)
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class Payroll {

    private Long id;

    private UUID employeeId;

    private BigDecimal baseSalary;

    @Builder.Default
    private BigDecimal totalBonuses = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal totalDiscounts = BigDecimal.ZERO;

    private BigDecimal totalPaid;

    private LocalDate paymentDate;

    private LocalDate fromDate;

    private LocalDate toDate;

    @Builder.Default
    private Boolean deleted = false;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    public void checkValidRegistration() {
        requirePositive(baseSalary, () -> new BadRequestException("El salario base es requerido"));
        requireNonNegative(totalBonuses, () -> new BadRequestException("Los bonos son requeridos"));
        requireNonNegative(totalDiscounts, () -> new BadRequestException("Los descuentos son requeridos"));
        requireNonNull(paymentDate, () -> new BadRequestException("La fecha de pago es requerida"));
        requireNonNull(fromDate, () -> new BadRequestException("La fecha desde la cual se paga es requerida"));
        requireNonNull(toDate, () -> new BadRequestException("La fecha hasta la cual se paga es requerida"));
        totalPaid = baseSalary.add(totalBonuses).subtract(totalDiscounts);
    }

    public void updateFrom(Payroll updated) {
        this.baseSalary = firstNonNull(updated.baseSalary, this.baseSalary);
        this.totalBonuses = firstNonNull(updated.totalBonuses, this.totalBonuses);
        this.totalDiscounts = firstNonNull(updated.totalDiscounts, this.totalDiscounts);
        this.totalPaid = firstNonNull(updated.totalPaid, this.totalPaid);
        this.paymentDate = firstNonNull(updated.paymentDate, this.paymentDate);
        this.fromDate = firstNonNull(updated.fromDate, this.fromDate);
        this.toDate = firstNonNull(updated.toDate, this.toDate);
    }

    public Payroll diff(Payroll updated) {
        return updated.toBuilder()
                .baseSalary(updated.baseSalary.subtract(this.baseSalary == null ? BigDecimal.ZERO : this.baseSalary))
                .build();
    }

    public Payroll deleted() {
        return toBuilder()
                .baseSalary(baseSalary.negate())
                .build();
    }

    public void delete() {
        this.deleted = true;
        this.deletedAt = Instant.now();
    }
}
