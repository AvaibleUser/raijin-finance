package edu.raijin.finance.employee.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.employee.domain.model.Employee;

@Port
public interface UpdateEmployeePort {

    Optional<Employee> findById(UUID id);

    Employee update(Employee employee);
}