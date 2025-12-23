package edu.raijin.finance.salary.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.salary.domain.model.Suspension;

@Port
public interface RegisterSuspensionPort {

    boolean existsContractedEmployee(UUID employeeId);

    Suspension register(UUID employeeId, Suspension suspension);
}
