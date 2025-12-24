package edu.raijin.finance.employee.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.employee.domain.model.Contract;

@Port
public interface FindContractPort {

    Paged<Contract> fetchAll(UUID employeeId, Pageable pageable);

    Optional<Contract> findByIdAndEmployeeId(Long id, UUID employeeId);

    Optional<Contract> findCurrentByEmployeeId(UUID employeeId);
}
