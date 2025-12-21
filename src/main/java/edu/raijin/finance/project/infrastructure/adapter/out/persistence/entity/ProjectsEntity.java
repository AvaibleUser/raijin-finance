package edu.raijin.finance.project.infrastructure.adapter.out.persistence.entity;

import static jakarta.persistence.GenerationType.UUID;
import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;
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

@Entity(name = "projects")
@Table(name = "projects", schema = "employee")
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class ProjectsEntity {

    @Id
    @GeneratedValue(strategy = UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String client;

    private Boolean closed;

    private BigDecimal monthlyIncome;

    @Builder.Default
    private Boolean deleted = false;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    private Instant deletedAt;
}
