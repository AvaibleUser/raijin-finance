package edu.raijin.finance.employee.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.employee.domain.model.Contract;

@Port
public interface UpdateContractPort {

    boolean existsAnotherCurrentByEmployeeId(UUID id, UUID employeeId);

    Optional<Contract> findByIdAndEmployeeId(UUID id, UUID employeeId);

    Contract update(Contract contract);
}
