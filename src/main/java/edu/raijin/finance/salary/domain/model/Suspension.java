package edu.raijin.finance.salary.domain.model;

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
public class Suspension {

    private Long id;

    private UUID employeeId;

    private BigDecimal amount;

    private String reason;

    private LocalDate startDate;

    private LocalDate endDate;

    @Builder.Default
    private Boolean deleted = false;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    public void checkValidRegistration() {
        requirePositive(amount, () -> new BadRequestException("El salario a suspender es requerido"));
        requireNonBlank(reason, () -> new BadRequestException("La razón de la suspensión es requerida"));
        requireNonNull(startDate, () -> new BadRequestException("La fecha de inicio es requerida"));
        if (endDate != null && startDate.isAfter(endDate)) {
            throw new BadRequestException("La fecha de inicio no puede ser posterior a la de finalización");
        }
    }

    public void updateFrom(Suspension updated) {
        this.amount = firstNonNull(updated.amount, this.amount);
        this.reason = firstNonNull(updated.reason, this.reason);
        this.startDate = firstNonNull(updated.startDate, this.startDate);
        this.endDate = firstNonNull(updated.endDate, this.endDate);
        this.createdAt = firstNonNull(updated.createdAt, this.createdAt);
    }

    public void delete() {
        this.deleted = true;
        this.deletedAt = Instant.now();
    }
}
