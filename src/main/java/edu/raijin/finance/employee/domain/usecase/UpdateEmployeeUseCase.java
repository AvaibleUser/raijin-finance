package edu.raijin.finance.employee.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.employee.domain.model.Employee;

@UseCase
public interface UpdateEmployeeUseCase {

    Employee update(UUID employeeId, Employee employee);
}