package edu.raijin.finance.employee.infrastructure.adapter.out.persistence.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.employee.domain.model.Contract;
import edu.raijin.finance.employee.domain.port.persistence.FindContractPort;
import edu.raijin.finance.employee.domain.port.persistence.RegisterContractPort;
import edu.raijin.finance.employee.domain.port.persistence.UpdateContractPort;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.entity.ContractsEntity;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.entity.EmployeesEntity;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.mapper.ContractEntityMapper;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.repository.JpaContractRepository;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.repository.JpaEmployeeRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class ContractRepositoryAdapter implements FindContractPort, RegisterContractPort, UpdateContractPort {

    private final JpaContractRepository contractRepository;
    private final JpaEmployeeRepository employeeRepository;
    private final ContractEntityMapper mapper;

    @Override
    public Contract register(UUID employeeId, Contract contract) {
        EmployeesEntity employee = employeeRepository.findById(employeeId).get();
        ContractsEntity entity = mapper.toEntity(contract).withEmployee(employee);
        return mapper.toDomain(contractRepository.save(entity));
    }

    @Override
    public Contract update(Contract contract) {
        ContractsEntity entity = mapper.toEntity(contract);
        return mapper.toDomain(contractRepository.save(entity));
    }

    @Override
    public Optional<Contract> findByIdAndEmployeeId(UUID id, UUID employeeId) {
        return contractRepository.findByIdAndEmployeeIdAndDeletedFalse(id, employeeId)
                .map(mapper::toDomain);
    }

    @Override
    public Paged<Contract> fetchAll(UUID employeeId, Pageable pageable) {
        Page<ContractsEntity> page = contractRepository.findByEmployeeIdAndDeletedFalse(employeeId, pageable);
        return Paged.from(page.map(mapper::toDomain));
    }

    @Override
    public boolean existsAnotherCurrentByEmployeeId(UUID id, UUID employeeId) {
        return contractRepository.existsAnotherCurrentByEmployeeId(id, employeeId);
    }

    @Override
    public boolean existsEmployee(UUID employeeId) {
        return employeeRepository.existsByIdAndDeletedFalse(employeeId);
    }

    @Override
    public boolean existsCurrentByEmployeeId(UUID employeeId) {
        return contractRepository.existsEmployeeCurrentContract(employeeId);
    }

    @Override
    public Optional<Contract> findCurrentByEmployeeId(UUID employeeId) {
        return contractRepository.findEmployeeCurrentContract(employeeId)
                .map(mapper::toDomain);
    }
}
