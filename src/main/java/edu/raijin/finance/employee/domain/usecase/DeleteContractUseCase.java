package edu.raijin.finance.employee.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;

@UseCase
public interface DeleteContractUseCase {

    void delete(UUID employeeId, UUID contractId);
}
