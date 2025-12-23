package edu.raijin.finance.salary.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.salary.domain.model.Bonus;

@Port
public interface UpdateBonusPort {

    Optional<Bonus> findByIdAndEmployeeId(Long id, UUID employeeId);

    Bonus update(Bonus bonus);
}
