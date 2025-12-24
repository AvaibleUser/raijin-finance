package edu.raijin.finance.finance.infrastructure.adapter.in.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.finance.domain.model.Expense;
import edu.raijin.finance.finance.domain.usecase.CreateExpenseUseCase;
import edu.raijin.finance.finance.domain.usecase.DeleteExpenseUseCase;
import edu.raijin.finance.finance.domain.usecase.FetchExpenseUseCase;
import edu.raijin.finance.finance.domain.usecase.UpdateExpenseUseCase;
import edu.raijin.finance.finance.infrastructure.adapter.in.rest.dto.expense.AddExpenseDto;
import edu.raijin.finance.finance.infrastructure.adapter.in.rest.dto.expense.ExpenseDto;
import edu.raijin.finance.finance.infrastructure.adapter.in.rest.mapper.ExpenseDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping({ "/projects/{projectId}/expenses", "/expenses" })
@RequiredArgsConstructor
public class ExpenseController {

    private final FetchExpenseUseCase fetch;
    private final CreateExpenseUseCase create;
    private final UpdateExpenseUseCase update;
    private final DeleteExpenseUseCase delete;
    private final ExpenseDtoMapper mapper;

    @GetMapping
    public Paged<ExpenseDto> fetchPaged(@PathVariable(required = false) UUID projectId, Pageable pageable) {
        return fetch.fetch(projectId, pageable).map(mapper::toDto);
    }

    @GetMapping("/{expenseId}")
    public ExpenseDto fetch(@PathVariable(required = false) UUID projectId, @PathVariable Long expenseId) {
        Expense expense = fetch.fetch(projectId, expenseId);
        return mapper.toDto(expense);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ExpenseDto create(@PathVariable(required = false) UUID projectId,
            @RequestBody @Valid AddExpenseDto expense) {
        Expense created = create.create(projectId, expense.employeeId(), mapper.toDomain(expense));
        return mapper.toDto(created);
    }

    @PutMapping("/{expenseId}")
    public ExpenseDto update(@PathVariable(required = false) UUID projectId, @PathVariable Long expenseId,
            @RequestBody AddExpenseDto expense) {
        Expense updated = update.update(projectId, expenseId, mapper.toDomain(expense));
        return mapper.toDto(updated);
    }

    @DeleteMapping("/{expenseId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable(required = false) UUID projectId, @PathVariable Long expenseId) {
        delete.delete(projectId, expenseId);
    }
}
