package edu.raijin.finance.finance.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.finance.finance.infrastructure.adapter.out.persistence.entity.ExpensesEntity;

@Repository
public interface JpaExpenseRepository extends JpaRepository<ExpensesEntity, Long> {

    Optional<ExpensesEntity> findByIdAndProjectIdAndDeletedFalse(Long id, UUID projectId);

    Page<ExpensesEntity> findByProjectIdAndDeletedFalse(UUID projectId, Pageable pageable);
}
