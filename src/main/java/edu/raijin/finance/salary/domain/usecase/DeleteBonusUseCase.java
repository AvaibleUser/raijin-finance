package edu.raijin.finance.salary.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;

@UseCase
public interface DeleteBonusUseCase {

    void delete(UUID employeeId, Long bonusId);
}
