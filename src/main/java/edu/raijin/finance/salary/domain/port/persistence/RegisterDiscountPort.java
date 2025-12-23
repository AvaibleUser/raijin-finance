package edu.raijin.finance.salary.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.salary.domain.model.Discount;

@Port
public interface RegisterDiscountPort {

    boolean existsContractedEmployee(UUID employeeId);

    Discount register(UUID employeeId, Discount discount);
}
