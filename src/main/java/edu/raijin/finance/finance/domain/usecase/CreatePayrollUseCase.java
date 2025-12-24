package edu.raijin.finance.finance.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.finance.domain.model.Payroll;

@UseCase
public interface CreatePayrollUseCase {

    Payroll create(UUID employeeId, Payroll payroll);
}