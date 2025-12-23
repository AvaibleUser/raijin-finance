package edu.raijin.finance.salary.infrastructure.adapter.in.rest.controller;

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
import edu.raijin.finance.salary.domain.model.Suspension;
import edu.raijin.finance.salary.domain.usecase.CreateSuspensionUseCase;
import edu.raijin.finance.salary.domain.usecase.DeleteSuspensionUseCase;
import edu.raijin.finance.salary.domain.usecase.FetchSuspensionUseCase;
import edu.raijin.finance.salary.domain.usecase.UpdateSuspensionUseCase;
import edu.raijin.finance.salary.infrastructure.adapter.in.rest.dto.suspension.AddSuspensionDto;
import edu.raijin.finance.salary.infrastructure.adapter.in.rest.dto.suspension.SuspensionDto;
import edu.raijin.finance.salary.infrastructure.adapter.in.rest.mapper.SuspensionDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/employees/{employeeId}/suspensions")
@RequiredArgsConstructor
public class SuspensionController {

    private final FetchSuspensionUseCase fetch;
    private final CreateSuspensionUseCase create;
    private final UpdateSuspensionUseCase update;
    private final DeleteSuspensionUseCase delete;
    private final SuspensionDtoMapper mapper;

    @GetMapping
    public Paged<SuspensionDto> fetchPaged(@PathVariable UUID employeeId, Pageable pageable) {
        return fetch.fetch(employeeId, pageable).map(mapper::toDto);
    }

    @GetMapping("/{suspensionId}")
    public SuspensionDto fetch(@PathVariable UUID employeeId, @PathVariable Long suspensionId) {
        Suspension suspension = fetch.fetch(employeeId, suspensionId);
        return mapper.toDto(suspension);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public SuspensionDto create(@PathVariable UUID employeeId, @RequestBody @Valid AddSuspensionDto suspension) {
        Suspension created = create.create(employeeId, mapper.toDomain(suspension));
        return mapper.toDto(created);
    }

    @PutMapping("/{suspensionId}")
    public SuspensionDto update(@PathVariable UUID employeeId, @PathVariable Long suspensionId,
            @RequestBody AddSuspensionDto suspension) {
        Suspension updated = update.update(employeeId, suspensionId, mapper.toDomain(suspension));
        return mapper.toDto(updated);
    }

    @DeleteMapping("/{suspensionId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable UUID employeeId, @PathVariable Long suspensionId) {
        delete.delete(employeeId, suspensionId);
    }
}
