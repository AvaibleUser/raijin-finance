package edu.raijin.finance.salary.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;

@UseCase
public interface DeleteSuspensionUseCase {

    void delete(UUID employeeId, Long suspensionId);
}
