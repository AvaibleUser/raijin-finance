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
public interface JpaContractRepository extends JpaRepository<ContractsEntity, UUID> {

    Optional<ContractsEntity> findByIdAndDeletedFalse(UUID id);

    Optional<ContractsEntity> findByIdAndEmployeeIdAndDeletedFalse(UUID id, UUID employeeId);

    Optional<ContractsEntity> findByEmployeeIdAndStatusNotAndDeletedFalse(UUID employeeId, ContractStatus status);

    Page<ContractsEntity> findByEmployeeIdAndDeletedFalse(UUID employeeId, Pageable pageable);

    boolean existsByIdAndDeletedFalse(UUID id);

    boolean existsByEmployeeIdAndStatusNotAndDeletedFalse(UUID employeeId, ContractStatus status);

    boolean existsByIdNotAndEmployeeIdAndStatusNotAndDeletedFalse(UUID id, UUID employeeId, ContractStatus status);

    default Optional<ContractsEntity> findEmployeeCurrentContract(UUID employeeId) {
        return findByEmployeeIdAndStatusNotAndDeletedFalse(employeeId, ContractStatus.TERMINATED);
    }

    default boolean existsEmployeeCurrentContract(UUID employeeId) {
        return existsByEmployeeIdAndStatusNotAndDeletedFalse(employeeId, ContractStatus.TERMINATED);
    }

    default boolean existsAnotherCurrentByEmployeeId(UUID id, UUID employeeId) {
        return existsByIdNotAndEmployeeIdAndStatusNotAndDeletedFalse(id, employeeId, ContractStatus.TERMINATED);
    }
}
