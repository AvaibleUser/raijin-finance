package edu.raijin.finance.employee.infrastructure.adapter.out.persistence.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.employee.domain.model.Employee;
import edu.raijin.finance.employee.domain.port.persistence.RegisterEmployeePort;
import edu.raijin.finance.employee.domain.port.persistence.UpdateEmployeePort;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.entity.EmployeesEntity;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.mapper.EmployeeEntityMapper;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.repository.JpaEmployeeRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class EmployeeRepositoryAdapter implements RegisterEmployeePort, UpdateEmployeePort {

    private final JpaEmployeeRepository employeeRepository;
    private final EmployeeEntityMapper mapper;

    @Override
    public UUID register(Employee employee) {
        EmployeesEntity entity = mapper.toEntity(employee);
        return employeeRepository.save(entity).getId();
    }

    @Override
    public Employee update(Employee employee) {
        EmployeesEntity entity = mapper.toEntity(employee);
        return mapper.toDomain(employeeRepository.save(entity));
    }

    @Override
    public Optional<Employee> findById(UUID id) {
        return employeeRepository.findByIdAndDeletedFalse(id)
                .map(mapper::toDomain);
    }
}
