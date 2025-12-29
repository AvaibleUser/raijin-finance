package edu.raijin.finance.finance.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.finance.finance.domain.model.Expense;
import edu.raijin.finance.finance.domain.port.messaging.DeletedExpensePublisherPort;
import edu.raijin.finance.finance.domain.port.persistence.UpdateExpensePort;
import edu.raijin.finance.finance.domain.usecase.DeleteExpenseUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteExpenseService implements DeleteExpenseUseCase {

    private final UpdateExpensePort update;
    private final DeletedExpensePublisherPort publisher;

    @Override
    @Transactional
    public void delete(UUID projectId, Long expenseId) {
        Expense expense = update.findByIdAndProjectId(expenseId, projectId).orElse(null);
        if (expense == null) {
            return;
        }

        Expense deleted = expense.deleted();
        expense.delete();
        update.update(expense);

        publisher.publishDeletedExpense(deleted);
    }
}
