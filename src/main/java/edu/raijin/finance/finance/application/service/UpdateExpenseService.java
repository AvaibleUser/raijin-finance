package edu.raijin.finance.finance.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.finance.domain.model.Expense;
import edu.raijin.finance.finance.domain.port.messaging.UpdatedExpensePublisherPort;
import edu.raijin.finance.finance.domain.port.persistence.UpdateExpensePort;
import edu.raijin.finance.finance.domain.usecase.UpdateExpenseUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateExpenseService implements UpdateExpenseUseCase {

    private final UpdateExpensePort update;
    private final UpdatedExpensePublisherPort publisher;

    @Override
    @Transactional
    public Expense update(UUID projectId, Long expenseId, Expense patches) {
        Expense expense = update.findByIdAndProjectId(expenseId, projectId)
                .orElseThrow(() -> new ValueNotFoundException("El gasto no se encuentra registrado"));

        Expense diff = expense.diff(patches);
        expense.updateFrom(patches);
        expense.checkValidRegistration();

        Expense updated = update.update(expense);
        publisher.publishUpdatedExpense(diff);
        return updated;
    }
}