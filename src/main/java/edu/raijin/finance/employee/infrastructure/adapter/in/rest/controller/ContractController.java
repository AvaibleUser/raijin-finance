package edu.raijin.finance.employee.infrastructure.adapter.in.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.employee.domain.model.Contract;
import edu.raijin.finance.employee.domain.usecase.CreateContractUseCase;
import edu.raijin.finance.employee.domain.usecase.DeleteContractUseCase;
import edu.raijin.finance.employee.domain.usecase.FetchContractUseCase;
import edu.raijin.finance.employee.domain.usecase.UpdateContractUseCase;
import edu.raijin.finance.employee.infrastructure.adapter.in.rest.dto.contract.AddContractDto;
import edu.raijin.finance.employee.infrastructure.adapter.in.rest.dto.contract.ContractDto;
import edu.raijin.finance.employee.infrastructure.adapter.in.rest.mapper.ContractDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/employees/{employeeId}/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final FetchContractUseCase fetch;
    private final CreateContractUseCase create;
    private final UpdateContractUseCase update;
    private final DeleteContractUseCase delete;
    private final ContractDtoMapper mapper;

    @GetMapping("/current")
    public ContractDto fetch(@PathVariable UUID employeeId) {
        Contract contract = fetch.fetchEmployeeCurrentContract(employeeId);
        return mapper.toDto(contract);
    }

    @GetMapping("/{contractId}")
    public ContractDto fetch(@PathVariable UUID employeeId, @PathVariable Long contractId) {
        Contract contract = fetch.fetch(employeeId, contractId);
        return mapper.toDto(contract);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ContractDto create(@PathVariable UUID employeeId, @RequestBody @Valid AddContractDto contract) {
        Contract created = create.create(employeeId, mapper.toDomain(contract));
        return mapper.toDto(created);
    }

    @PutMapping("/{contractId}")
    public ContractDto update(@PathVariable UUID employeeId, @PathVariable Long contractId,
            @RequestBody AddContractDto contract) {
        Contract updated = update.update(employeeId, contractId, mapper.toDomain(contract));
        return mapper.toDto(updated);
    }

    @DeleteMapping("/{contractId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable UUID employeeId, @PathVariable Long contractId) {
        delete.delete(employeeId, contractId);
    }
}
