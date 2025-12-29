package edu.raijin.finance.finance.domain.port.messaging;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.finance.domain.model.Expense;

@Port
public interface DeletedExpensePublisherPort {

    void publishDeletedExpense(Expense expense);
}
