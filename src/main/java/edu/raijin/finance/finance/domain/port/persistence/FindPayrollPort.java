package edu.raijin.finance.finance.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.finance.domain.model.Payroll;

@Port
public interface FindPayrollPort {

    Optional<Payroll> findByIdAndEmployeeId(Long id, UUID employeeId);

    Paged<Payroll> fetchAll(UUID employeeId, Pageable pageable);
}
