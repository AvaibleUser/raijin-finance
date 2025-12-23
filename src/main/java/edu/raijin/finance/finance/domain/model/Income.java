package edu.raijin.finance.finance.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static edu.raijin.commons.util.exception.Exceptions.requirePositive;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import edu.raijin.commons.domain.type.IncomeType;
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
public class Income {

    private Long id;

    private UUID projectId;

    private BigDecimal amount;

    private IncomeType type;

    private String description;

    private LocalDate billingDate;

    @Builder.Default
    private Boolean deleted = false;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    public void checkValidRegistration() {
        requirePositive(amount, () -> new BadRequestException("El ingreso es requerido"));
        requireNonNull(type, () -> new BadRequestException("El tipo de ingreso es requerido"));
        requireNonNull(billingDate, () -> new BadRequestException("La fecha de facturación es requerida"));
    }

    public void updateFrom(Income updated) {
        this.amount = firstNonNull(updated.amount, this.amount);
        this.type = firstNonNull(updated.type, this.type);
        this.description = firstNonNull(updated.description, this.description);
        this.billingDate = firstNonNull(updated.billingDate, this.billingDate);
    }

    public void delete() {
        this.deleted = true;
        this.deletedAt = Instant.now();
    }
}
