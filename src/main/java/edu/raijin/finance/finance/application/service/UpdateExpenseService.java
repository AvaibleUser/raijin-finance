package edu.raijin.finance.finance.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.finance.domain.model.Expense;
import edu.raijin.finance.finance.domain.port.persistence.UpdateExpensePort;
import edu.raijin.finance.finance.domain.usecase.UpdateExpenseUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateExpenseService implements UpdateExpenseUseCase {

    private final UpdateExpensePort update;

    @Override
    @Transactional
    public Expense update(UUID projectId, Long expenseId, Expense patches) {
        Expense expense = update.findByIdAndProjectId(expenseId, projectId)
                .orElseThrow(() -> new ValueNotFoundException("El gasto no se encuentra registrado"));

        expense.updateFrom(patches);
        expense.checkValidRegistration();

        return update.update(expense);
    }
}