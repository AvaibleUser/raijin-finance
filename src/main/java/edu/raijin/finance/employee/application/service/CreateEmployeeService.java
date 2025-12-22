package edu.raijin.finance.employee.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.finance.employee.domain.model.Employee;
import edu.raijin.finance.employee.domain.port.persistence.RegisterEmployeePort;
import edu.raijin.finance.employee.domain.usecase.CreateEmployeeUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateEmployeeService implements CreateEmployeeUseCase {

    private final RegisterEmployeePort register;

    @Override
    @Transactional
    public UUID create(Employee employee) {
        employee.checkValidRegistration();
        return register.register(employee);
    }
}