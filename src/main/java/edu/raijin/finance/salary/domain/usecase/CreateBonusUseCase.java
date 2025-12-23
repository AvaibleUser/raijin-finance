package edu.raijin.finance.salary.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.salary.domain.model.Bonus;

@UseCase
public interface CreateBonusUseCase {

    Bonus create(UUID employeeId, Bonus bonus);
}