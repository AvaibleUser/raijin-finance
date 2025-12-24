package edu.raijin.finance.finance.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.finance.finance.infrastructure.adapter.out.persistence.entity.PayrollEntity;

@Repository
public interface JpaPayrollRepository extends JpaRepository<PayrollEntity, Long> {

    Optional<PayrollEntity> findByIdAndEmployeeIdAndDeletedFalse(Long id, UUID employeeId);

    Page<PayrollEntity> findByEmployeeIdAndDeletedFalse(UUID employeeId, Pageable pageable);
}
