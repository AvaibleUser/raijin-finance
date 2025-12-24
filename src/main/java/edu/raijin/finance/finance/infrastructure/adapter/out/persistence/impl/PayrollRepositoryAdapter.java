package edu.raijin.finance.finance.infrastructure.adapter.out.persistence.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.entity.EmployeesEntity;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.repository.JpaEmployeeRepository;
import edu.raijin.finance.finance.domain.model.Payroll;
import edu.raijin.finance.finance.domain.port.persistence.FindPayrollPort;
import edu.raijin.finance.finance.domain.port.persistence.RegisterPayrollPort;
import edu.raijin.finance.finance.domain.port.persistence.UpdatePayrollPort;
import edu.raijin.finance.finance.infrastructure.adapter.out.persistence.entity.PayrollEntity;
import edu.raijin.finance.finance.infrastructure.adapter.out.persistence.mapper.PayrollEntityMapper;
import edu.raijin.finance.finance.infrastructure.adapter.out.persistence.repository.JpaPayrollRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class PayrollRepositoryAdapter implements FindPayrollPort, RegisterPayrollPort, UpdatePayrollPort {

    private final JpaPayrollRepository payrollRepository;
    private final JpaEmployeeRepository employeeRepository;
    private final PayrollEntityMapper mapper;

    @Override
    public Payroll register(UUID employeeId, Payroll payroll) {
        EmployeesEntity employee = employeeRepository.findById(employeeId).get();
        PayrollEntity entity = mapper.toEntity(payroll).withEmployee(employee);
        return mapper.toDomain(payrollRepository.save(entity));
    }

    @Override
    public Payroll update(Payroll payroll) {
        PayrollEntity entity = mapper.toEntity(payroll);
        return mapper.toDomain(payrollRepository.save(entity));
    }

    @Override
    public Optional<Payroll> findByIdAndEmployeeId(Long id, UUID employeeId) {
        return payrollRepository.findByIdAndEmployeeIdAndDeletedFalse(id, employeeId)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsEmployee(UUID employeeId) {
        return employeeRepository.existsByIdAndDeletedFalse(employeeId);
    }

    @Override
    public Paged<Payroll> fetchAll(UUID employeeId, Pageable pageable) {
        Page<PayrollEntity> page = payrollRepository.findByEmployeeIdAndDeletedFalse(employeeId, pageable);
        return Paged.from(page.map(mapper::toDomain));
    }
}
