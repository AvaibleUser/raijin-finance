package edu.raijin.finance.salary.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.salary.domain.model.Suspension;

@Port
public interface FindSuspensionPort {

    Optional<Suspension> findByIdAndEmployeeId(Long id, UUID employeeId);

    Paged<Suspension> fetchAll(UUID employeeId, Pageable pageable);
}
