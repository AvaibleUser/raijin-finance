package edu.raijin.finance.employee.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonBlank;
import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

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
public class Employee {

    private UUID id;

    private String firstName;

    private String lastName;

    private String dpi;

    private String email;

    @Builder.Default
    private Boolean hired = false;

    @Builder.Default
    private Boolean deleted = false;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    public void checkValidRegistration() {
        requireNonNull(id, () -> new BadRequestException("El usuario es requerido"));
        requireNonBlank(firstName, () -> new BadRequestException("El nombre es requerido"));
        requireNonBlank(lastName, () -> new BadRequestException("El apellido es requerido"));
        requireNonBlank(dpi, () -> new BadRequestException("El DPI es requerido"));
        requireNonBlank(email, () -> new BadRequestException("El correo es requerido"));
        requireNonNull(hired, () -> new BadRequestException("El estado es requerido"));
    }

    public void updateFrom(Employee updated) {
        this.firstName = firstNonNull(updated.firstName, this.firstName);
        this.lastName = firstNonNull(updated.lastName, this.lastName);
        this.dpi = firstNonNull(updated.dpi, this.dpi);
        this.email = firstNonNull(updated.email, this.email);
        this.hired = firstNonNull(updated.hired, this.hired);
    }

    public void delete() {
        this.deleted = true;
        this.deletedAt = Instant.now();
    }
}
