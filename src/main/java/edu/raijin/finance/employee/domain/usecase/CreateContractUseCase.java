package edu.raijin.finance.employee.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.employee.domain.model.Contract;

@UseCase
public interface CreateContractUseCase {

    Contract create(UUID employeeId, Contract contract);
}
