package edu.raijin.finance.employee.infrastructure.adapter.out.persistence.entity;

import static jakarta.persistence.GenerationType.UUID;
import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "employees")
@Table(name = "employees", schema = "employee")
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class EmployeesEntity {

    @Id
    @GeneratedValue(strategy = UUID)
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String dpi;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean hired;

    @Builder.Default
    @Column(nullable = false)
    private Boolean deleted = false;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    private Instant deletedAt;
}
