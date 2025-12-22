package edu.raijin.finance.employee.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.entity.EmployeesEntity;

@Repository
public interface JpaEmployeeRepository extends JpaRepository<EmployeesEntity, UUID> {

    Optional<EmployeesEntity> findByIdAndDeletedFalse(UUID id);

    boolean existsByIdAndDeletedFalse(UUID id);
}
