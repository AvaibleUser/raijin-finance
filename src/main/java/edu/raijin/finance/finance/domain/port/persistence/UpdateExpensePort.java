package edu.raijin.finance.finance.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.finance.domain.model.Expense;

@Port
public interface UpdateExpensePort {

    Optional<Expense> findByIdAndProjectId(Long id, UUID projectId);

    Expense update(Expense expense);
}
