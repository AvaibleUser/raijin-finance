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
import edu.raijin.finance.finance.domain.model.Income;
import edu.raijin.finance.finance.domain.usecase.CreateIncomeUseCase;
import edu.raijin.finance.finance.domain.usecase.DeleteIncomeUseCase;
import edu.raijin.finance.finance.domain.usecase.FetchIncomeUseCase;
import edu.raijin.finance.finance.domain.usecase.UpdateIncomeUseCase;
import edu.raijin.finance.finance.infrastructure.adapter.in.rest.dto.income.AddIncomeDto;
import edu.raijin.finance.finance.infrastructure.adapter.in.rest.dto.income.IncomeDto;
import edu.raijin.finance.finance.infrastructure.adapter.in.rest.mapper.IncomeDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/projects/{projectId}/incomes")
@RequiredArgsConstructor
public class IncomeController {

    private final FetchIncomeUseCase fetch;
    private final CreateIncomeUseCase create;
    private final UpdateIncomeUseCase update;
    private final DeleteIncomeUseCase delete;
    private final IncomeDtoMapper mapper;

    @GetMapping
    public Paged<IncomeDto> fetchPaged(@PathVariable UUID projectId, Pageable pageable) {
        return fetch.fetch(projectId, pageable).map(mapper::toDto);
    }

    @GetMapping("/{incomeId}")
    public IncomeDto fetch(@PathVariable UUID projectId, @PathVariable Long incomeId) {
        Income income = fetch.fetch(projectId, incomeId);
        return mapper.toDto(income);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public IncomeDto create(@PathVariable UUID projectId, @RequestBody @Valid AddIncomeDto income) {
        Income created = create.create(projectId, mapper.toDomain(income));
        return mapper.toDto(created);
    }

    @PutMapping("/{incomeId}")
    public IncomeDto update(@PathVariable UUID projectId, @PathVariable Long incomeId,
            @RequestBody AddIncomeDto income) {
        Income updated = update.update(projectId, incomeId, mapper.toDomain(income));
        return mapper.toDto(updated);
    }

    @DeleteMapping("/{incomeId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable UUID projectId, @PathVariable Long incomeId) {
        delete.delete(projectId, incomeId);
    }
}
