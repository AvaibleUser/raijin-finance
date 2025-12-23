package edu.raijin.finance.salary.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.salary.domain.model.Suspension;

@Port
public interface UpdateSuspensionPort {

    Optional<Suspension> findByIdAndEmployeeId(Long id, UUID employeeId);

    Suspension update(Suspension suspension);
}
