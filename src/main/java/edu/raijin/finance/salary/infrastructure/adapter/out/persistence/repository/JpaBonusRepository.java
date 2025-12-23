package edu.raijin.finance.salary.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.finance.salary.infrastructure.adapter.out.persistence.entity.BonusesEntity;

@Repository
public interface JpaBonusRepository extends JpaRepository<BonusesEntity, Long> {

    Optional<BonusesEntity> findByIdAndEmployeeIdAndDeletedFalse(Long id, UUID employeeId);

    Page<BonusesEntity> findByEmployeeIdAndDeletedFalse(UUID employeeId, Pageable pageable);
}
