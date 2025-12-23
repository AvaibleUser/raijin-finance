package edu.raijin.finance.salary.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.finance.salary.infrastructure.adapter.out.persistence.entity.SuspensionsEntity;

@Repository
public interface JpaSuspensionRepository extends JpaRepository<SuspensionsEntity, Long> {

    Optional<SuspensionsEntity> findByIdAndEmployeeIdAndDeletedFalse(Long id, UUID employeeId);

    Page<SuspensionsEntity> findByEmployeeIdAndDeletedFalse(UUID employeeId, Pageable pageable);
}
