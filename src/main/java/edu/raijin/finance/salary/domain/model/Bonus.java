package edu.raijin.finance.salary.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonBlank;
import static edu.raijin.commons.util.exception.Exceptions.requirePositive;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

import java.math.BigDecimal;
import java.time.Instant;
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
public class Bonus {

    private Long id;

    private UUID employeeId;

    private BigDecimal amount;

    private String reason;

    @Builder.Default
    private Boolean deleted = false;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    public void checkValidRegistration() {
        requirePositive(amount, () -> new BadRequestException("El salario a bonificar es requerido"));
        requireNonBlank(reason, () -> new BadRequestException("El motivo del bono es requerido"));
    }

    public void updateFrom(Bonus updated) {
        this.amount = firstNonNull(updated.amount, this.amount);
        this.reason = firstNonNull(updated.reason, this.reason);
        this.createdAt = firstNonNull(updated.createdAt, this.createdAt);
    }

    public Bonus diff(Bonus updated) {
        return updated.toBuilder()
                .amount(updated.amount.subtract(this.amount == null ? BigDecimal.ZERO : this.amount))
                .build();
    }

    public Bonus deleted() {
        return toBuilder()
                .amount(amount.negate())
                .build();
    }

    public void delete() {
        this.deleted = true;
        this.deletedAt = Instant.now();
    }
}
