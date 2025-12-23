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
import edu.raijin.finance.salary.domain.model.Bonus;
import edu.raijin.finance.salary.domain.usecase.CreateBonusUseCase;
import edu.raijin.finance.salary.domain.usecase.DeleteBonusUseCase;
import edu.raijin.finance.salary.domain.usecase.FetchBonusUseCase;
import edu.raijin.finance.salary.domain.usecase.UpdateBonusUseCase;
import edu.raijin.finance.salary.infrastructure.adapter.in.rest.dto.bonus.AddBonusDto;
import edu.raijin.finance.salary.infrastructure.adapter.in.rest.dto.bonus.BonusDto;
import edu.raijin.finance.salary.infrastructure.adapter.in.rest.mapper.BonusDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/employees/{employeeId}/bonuses")
@RequiredArgsConstructor
public class BonusController {

    private final FetchBonusUseCase fetch;
    private final CreateBonusUseCase create;
    private final UpdateBonusUseCase update;
    private final DeleteBonusUseCase delete;
    private final BonusDtoMapper mapper;

    @GetMapping
    public Paged<BonusDto> fetchPaged(@PathVariable UUID employeeId, Pageable pageable) {
        return fetch.fetch(employeeId, pageable).map(mapper::toDto);
    }

    @GetMapping("/{bonusId}")
    public BonusDto fetch(@PathVariable UUID employeeId, @PathVariable Long bonusId) {
        Bonus bonus = fetch.fetch(employeeId, bonusId);
        return mapper.toDto(bonus);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public BonusDto create(@PathVariable UUID employeeId, @RequestBody @Valid AddBonusDto bonus) {
        Bonus created = create.create(employeeId, mapper.toDomain(bonus));
        return mapper.toDto(created);
    }

    @PutMapping("/{bonusId}")
    public BonusDto update(@PathVariable UUID employeeId, @PathVariable Long bonusId, @RequestBody AddBonusDto bonus) {
        Bonus updated = update.update(employeeId, bonusId, mapper.toDomain(bonus));
        return mapper.toDto(updated);
    }

    @DeleteMapping("/{bonusId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable UUID employeeId, @PathVariable Long bonusId) {
        delete.delete(employeeId, bonusId);
    }
}
