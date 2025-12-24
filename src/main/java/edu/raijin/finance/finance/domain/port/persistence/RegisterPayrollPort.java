package edu.raijin.finance.finance.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.finance.domain.model.Payroll;

@Port
public interface RegisterPayrollPort {

    boolean existsEmployee(UUID employeeId);

    Payroll register(UUID employeeId, Payroll payroll);
}
