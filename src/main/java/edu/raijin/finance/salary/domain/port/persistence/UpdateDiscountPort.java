package edu.raijin.finance.salary.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.salary.domain.model.Discount;

@Port
public interface UpdateDiscountPort {

    Optional<Discount> findByIdAndEmployeeId(Long id, UUID employeeId);

    Discount update(Discount discount);
}
