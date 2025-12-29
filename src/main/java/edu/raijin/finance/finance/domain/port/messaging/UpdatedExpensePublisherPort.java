package edu.raijin.finance.finance.domain.port.messaging;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.finance.domain.model.Expense;

@Port
public interface UpdatedExpensePublisherPort {

    void publishUpdatedExpense(Expense expense);
}
