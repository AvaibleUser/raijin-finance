package edu.raijin.finance.finance.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.finance.domain.model.Expense;

@UseCase
public interface CreateExpenseUseCase {

    Expense create(UUID projectId, UUID employeeId, Expense expense);
}