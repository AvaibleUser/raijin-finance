package edu.raijin.finance.salary.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.salary.domain.model.Suspension;

@UseCase
public interface UpdateSuspensionUseCase {

    Suspension update(UUID employeeId, Long suspensionId, Suspension suspension);
}
