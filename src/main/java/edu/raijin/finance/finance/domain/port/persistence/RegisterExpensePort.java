package edu.raijin.finance.finance.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.finance.domain.model.Expense;

@Port
public interface RegisterExpensePort {

    boolean existsProject(UUID projectId);

    boolean existsEmployee(UUID employeeId);

    Expense register(UUID projectId, UUID employeeId, Expense expense);
}
