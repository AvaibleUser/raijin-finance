package edu.raijin.finance.employee.domain.usecase;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.employee.domain.model.Contract;

@UseCase
public interface FetchContractUseCase {

    Contract fetch(UUID employeeId, Long contractId);

    Paged<Contract> fetchAll(UUID employeeId, Pageable pageable);

    Contract fetchEmployeeCurrentContract(UUID employeeId);
}
