package edu.raijin.finance.salary.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.salary.domain.model.Bonus;

@UseCase
public interface UpdateBonusUseCase {

    Bonus update(UUID employeeId, Long bonusId, Bonus bonus);
}
