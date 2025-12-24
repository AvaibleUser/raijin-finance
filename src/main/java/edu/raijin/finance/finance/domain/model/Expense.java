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

import edu.raijin.commons.domain.type.ExpenseType;
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
public class Expense {

    private Long id;

    private UUID projectId;

    private UUID employeeId;

    private String description;

    private BigDecimal amount;

    private ExpenseType type;

    private LocalDate expenseDate;

    @Builder.Default
    private Boolean deleted = false;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    public void checkValidRegistration() {
        requireNonNull(description, () -> new BadRequestException("La descripción es requerida"));
        requireNonNull(type, () -> new BadRequestException("El tipo de gasto es requerido"));
        requirePositive(amount, () -> new BadRequestException("El monto del gasto es requerido"));
        requireNonNull(expenseDate, () -> new BadRequestException("La fecha del gasto es requerida"));
    }

    public void updateFrom(Expense updated) {
        this.description = firstNonNull(updated.description, this.description);
        this.amount = firstNonNull(updated.amount, this.amount);
        this.type = firstNonNull(updated.type, this.type);
        this.expenseDate = firstNonNull(updated.expenseDate, this.expenseDate);
    }

    public void delete() {
        this.deleted = true;
        this.deletedAt = Instant.now();
    }
}
