package edu.raijin.finance.project.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonBlank;
import static edu.raijin.commons.util.exception.Exceptions.requireNonNegative;
import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
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
@Builder
@Setter(NONE)
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class Project {

    private UUID id;

    private String name;

    private String client;

    private Boolean closed;

    private BigDecimal monthlyIncome;

    @Builder.Default
    private Boolean deleted = false;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    public void checkValidRegistration() {
        requireNonBlank(name, () -> new BadRequestException("El nombre del proyecto es requerido"));
        requireNonNull(closed, () -> new BadRequestException("El estado del proyecto es requerido"));
        requireNonNegative(monthlyIncome, () -> new BadRequestException("El ingreso mensual es requerido"));
    }

    public void updateFrom(Project updated) {
        this.name = firstNonNull(updated.name, this.name);
        this.client = firstNonNull(updated.client, this.client);
        this.closed = firstNonNull(updated.closed, this.closed);
        this.monthlyIncome = firstNonNull(updated.monthlyIncome, this.monthlyIncome);
    }

    public void delete() {
        this.deleted = true;
        this.deletedAt = Instant.now();
    }
}
