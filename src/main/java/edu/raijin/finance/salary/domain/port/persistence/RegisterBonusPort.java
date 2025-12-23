package edu.raijin.finance.salary.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.salary.domain.model.Bonus;

@Port
public interface RegisterBonusPort {

    boolean existsContractedEmployee(UUID employeeId);

    Bonus register(UUID employeeId, Bonus bonus);
}
