package edu.raijin.finance.employee.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.commons.domain.type.ContractStatus;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.entity.ContractsEntity;

@Repository
public interface JpaContractRepository extends JpaRepository<ContractsEntity, Long> {

    Optional<ContractsEntity> findByIdAndDeletedFalse(Long id);

    Optional<ContractsEntity> findByIdAndEmployeeIdAndDeletedFalse(Long id, UUID employeeId);

    Optional<ContractsEntity> findByEmployeeIdAndStatusNotAndDeletedFalse(UUID employeeId, ContractStatus status);

    Page<ContractsEntity> findByEmployeeIdAndDeletedFalse(UUID employeeId, Pageable pageable);

    boolean existsByIdAndDeletedFalse(Long id);

    boolean existsByEmployeeIdAndStatusNotAndDeletedFalse(UUID employeeId, ContractStatus status);

    boolean existsByIdNotAndEmployeeIdAndStatusNotAndDeletedFalse(Long id, UUID employeeId, ContractStatus status);

    default Optional<ContractsEntity> findEmployeeCurrentContract(UUID employeeId) {
        return findByEmployeeIdAndStatusNotAndDeletedFalse(employeeId, ContractStatus.TERMINATED);
    }

    default boolean existsEmployeeCurrentContract(UUID employeeId) {
        return existsByEmployeeIdAndStatusNotAndDeletedFalse(employeeId, ContractStatus.TERMINATED);
    }

    default boolean existsAnotherCurrentByEmployeeId(Long id, UUID employeeId) {
        return existsByIdNotAndEmployeeIdAndStatusNotAndDeletedFalse(id, employeeId, ContractStatus.TERMINATED);
    }
}
