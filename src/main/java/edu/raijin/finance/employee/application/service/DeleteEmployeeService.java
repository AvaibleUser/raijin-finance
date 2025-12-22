package edu.raijin.finance.employee.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.finance.employee.domain.model.Employee;
import edu.raijin.finance.employee.domain.port.persistence.UpdateEmployeePort;
import edu.raijin.finance.employee.domain.usecase.DeleteEmployeeUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteEmployeeService implements DeleteEmployeeUseCase {

    private final UpdateEmployeePort update;

    @Override
    @Transactional
    public void delete(UUID employeeId) {
        Employee employee = update.findById(employeeId).orElse(null);
        if (employee == null) {
            return;
        }

        employee.delete();
        update.update(employee);
    }
}