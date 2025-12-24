package edu.raijin.finance.finance.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.finance.domain.model.Expense;

@UseCase
public interface UpdateExpenseUseCase {

    Expense update(UUID projectId, Long expenseId, Expense expense);
}
