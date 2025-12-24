package edu.raijin.finance.finance.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;

@UseCase
public interface DeletePayrollUseCase {

    void delete(UUID employeeId, Long payrollId);
}
