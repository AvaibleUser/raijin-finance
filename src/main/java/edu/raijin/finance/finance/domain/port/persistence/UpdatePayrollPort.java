package edu.raijin.finance.finance.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.finance.domain.model.Payroll;

@Port
public interface UpdatePayrollPort {

    Optional<Payroll> findByIdAndEmployeeId(Long id, UUID employeeId);

    Payroll update(Payroll payroll);
}
