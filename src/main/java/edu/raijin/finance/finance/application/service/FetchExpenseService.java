package edu.raijin.finance.finance.application.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.finance.domain.model.Expense;
import edu.raijin.finance.finance.domain.port.persistence.FindExpensePort;
import edu.raijin.finance.finance.domain.usecase.FetchExpenseUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchExpenseService implements FetchExpenseUseCase {

    private final FindExpensePort find;

    @Override
    @Transactional
    public Expense fetch(UUID projectId, Long expenseId) {
        return find.findByIdAndProjectId(expenseId, projectId)
                .orElseThrow(() -> new ValueNotFoundException("El gasto no se encuentra registrado"));
    }

    @Override
    public Paged<Expense> fetch(UUID projectId, Pageable pageable) {
        return find.fetchAllByProject(projectId, pageable);
    }
}