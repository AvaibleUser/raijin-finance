package edu.raijin.finance.salary.infrastructure.adapter.out.persistence.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.entity.EmployeesEntity;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.repository.JpaContractRepository;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.repository.JpaEmployeeRepository;
import edu.raijin.finance.salary.domain.model.Suspension;
import edu.raijin.finance.salary.domain.port.persistence.FindSuspensionPort;
import edu.raijin.finance.salary.domain.port.persistence.RegisterSuspensionPort;
import edu.raijin.finance.salary.domain.port.persistence.UpdateSuspensionPort;
import edu.raijin.finance.salary.infrastructure.adapter.out.persistence.entity.SuspensionsEntity;
import edu.raijin.finance.salary.infrastructure.adapter.out.persistence.mapper.SuspensionEntityMapper;
import edu.raijin.finance.salary.infrastructure.adapter.out.persistence.repository.JpaSuspensionRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class SuspensionRepositoryAdapter implements FindSuspensionPort, RegisterSuspensionPort, UpdateSuspensionPort {

    private final JpaSuspensionRepository suspensionRepository;
    private final JpaEmployeeRepository employeeRepository;
    private final JpaContractRepository contractRepository;
    private final SuspensionEntityMapper mapper;

    @Override
    public Suspension register(UUID employeeId, Suspension suspension) {
        EmployeesEntity employee = employeeRepository.findById(employeeId).get();
        SuspensionsEntity entity = mapper.toEntity(suspension).withEmployee(employee);
        return mapper.toDomain(suspensionRepository.save(entity));
    }

    @Override
    public Suspension update(Suspension suspension) {
        SuspensionsEntity entity = mapper.toEntity(suspension);
        return mapper.toDomain(suspensionRepository.save(entity));
    }

    @Override
    public Optional<Suspension> findByIdAndEmployeeId(Long id, UUID employeeId) {
        return suspensionRepository.findByIdAndEmployeeIdAndDeletedFalse(id, employeeId)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsContractedEmployee(UUID employeeId) {
        return contractRepository.existsEmployeeCurrentContract(employeeId);
    }

    @Override
    public Paged<Suspension> fetchAll(UUID employeeId, Pageable pageable) {
        Page<SuspensionsEntity> page = suspensionRepository.findByEmployeeIdAndDeletedFalse(employeeId, pageable);
        return Paged.from(page.map(mapper::toDomain));
    }
}
