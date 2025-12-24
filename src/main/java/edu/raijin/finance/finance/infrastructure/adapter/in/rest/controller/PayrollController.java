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
import edu.raijin.finance.finance.domain.model.Payroll;
import edu.raijin.finance.finance.domain.usecase.CreatePayrollUseCase;
import edu.raijin.finance.finance.domain.usecase.DeletePayrollUseCase;
import edu.raijin.finance.finance.domain.usecase.FetchPayrollUseCase;
import edu.raijin.finance.finance.domain.usecase.UpdatePayrollUseCase;
import edu.raijin.finance.finance.infrastructure.adapter.in.rest.dto.payroll.AddPayrollDto;
import edu.raijin.finance.finance.infrastructure.adapter.in.rest.dto.payroll.PayrollDto;
import edu.raijin.finance.finance.infrastructure.adapter.in.rest.mapper.PayrollDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/employees/{employeeId}/payrolls")
@RequiredArgsConstructor
public class PayrollController {

    private final FetchPayrollUseCase fetch;
    private final CreatePayrollUseCase create;
    private final UpdatePayrollUseCase update;
    private final DeletePayrollUseCase delete;
    private final PayrollDtoMapper mapper;

    @GetMapping
    public Paged<PayrollDto> fetchPaged(@PathVariable UUID employeeId, Pageable pageable) {
        return fetch.fetch(employeeId, pageable).map(mapper::toDto);
    }

    @GetMapping("/{payrollId}")
    public PayrollDto fetch(@PathVariable UUID employeeId, @PathVariable Long payrollId) {
        Payroll payroll = fetch.fetch(employeeId, payrollId);
        return mapper.toDto(payroll);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public PayrollDto create(@PathVariable UUID employeeId, @RequestBody @Valid AddPayrollDto payroll) {
        Payroll created = create.create(employeeId, mapper.toDomain(payroll));
        return mapper.toDto(created);
    }

    @PutMapping("/{payrollId}")
    public PayrollDto update(@PathVariable UUID employeeId, @PathVariable Long payrollId,
            @RequestBody AddPayrollDto payroll) {
        Payroll updated = update.update(employeeId, payrollId, mapper.toDomain(payroll));
        return mapper.toDto(updated);
    }

    @DeleteMapping("/{payrollId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable UUID employeeId, @PathVariable Long payrollId) {
        delete.delete(employeeId, payrollId);
    }
}
