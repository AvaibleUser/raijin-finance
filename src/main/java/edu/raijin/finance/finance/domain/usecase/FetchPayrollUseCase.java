package edu.raijin.finance.finance.domain.usecase;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.finance.domain.model.Payroll;

@UseCase
public interface FetchPayrollUseCase {

    Payroll fetch(UUID employeeId, Long payrollId);

    Paged<Payroll> fetch(UUID employeeId, Pageable pageable);
}
