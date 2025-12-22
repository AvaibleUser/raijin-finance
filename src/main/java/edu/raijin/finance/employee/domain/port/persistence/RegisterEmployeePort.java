package edu.raijin.finance.employee.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.employee.domain.model.Employee;

@Port
public interface RegisterEmployeePort {

    UUID register(Employee employee);
}