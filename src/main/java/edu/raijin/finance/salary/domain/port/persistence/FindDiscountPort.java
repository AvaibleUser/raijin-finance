package edu.raijin.finance.salary.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.salary.domain.model.Discount;

@Port
public interface FindDiscountPort {

    Optional<Discount> findByIdAndEmployeeId(Long id, UUID employeeId);

    Paged<Discount> fetchAll(UUID employeeId, Pageable pageable);
}
