package edu.raijin.finance.employee.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.employee.domain.model.Contract;

@UseCase
public interface FetchContractUseCase {

    Contract fetch(UUID employeeId, Long contractId);

    Contract fetchEmployeeCurrentContract(UUID employeeId);
}
