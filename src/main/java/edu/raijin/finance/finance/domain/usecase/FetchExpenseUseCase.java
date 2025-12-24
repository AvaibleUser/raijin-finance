package edu.raijin.finance.finance.domain.usecase;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.finance.domain.model.Expense;

@UseCase
public interface FetchExpenseUseCase {

    Expense fetch(UUID projectId, Long expenseId);

    Paged<Expense> fetch(UUID projectId, Pageable pageable);
}
