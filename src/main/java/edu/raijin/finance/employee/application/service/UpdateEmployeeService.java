package edu.raijin.finance.employee.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.employee.domain.model.Employee;
import edu.raijin.finance.employee.domain.port.persistence.UpdateEmployeePort;
import edu.raijin.finance.employee.domain.usecase.UpdateEmployeeUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateEmployeeService implements UpdateEmployeeUseCase {

    private final UpdateEmployeePort update;

    @Override
    @Transactional
    public Employee update(UUID employeeId, Employee employee) {
        Employee toUpdate = update.findById(employeeId)
                .orElseThrow(() -> new ValueNotFoundException("El empleado no se encuentra registrado"));

        toUpdate.updateFrom(employee);
        toUpdate.checkValidRegistration();
        return update.update(toUpdate);
    }
}