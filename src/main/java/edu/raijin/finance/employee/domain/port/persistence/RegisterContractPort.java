package edu.raijin.finance.employee.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.employee.domain.model.Contract;

@Port
public interface RegisterContractPort {

    boolean existsEmployee(UUID employeeId);

    boolean existsCurrentByEmployeeId(UUID employeeId);

    Contract register(UUID employeeId, Contract contract);
}
