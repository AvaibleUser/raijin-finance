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
import edu.raijin.finance.salary.domain.model.Discount;
import edu.raijin.finance.salary.domain.usecase.CreateDiscountUseCase;
import edu.raijin.finance.salary.domain.usecase.DeleteDiscountUseCase;
import edu.raijin.finance.salary.domain.usecase.FetchDiscountUseCase;
import edu.raijin.finance.salary.domain.usecase.UpdateDiscountUseCase;
import edu.raijin.finance.salary.infrastructure.adapter.in.rest.dto.discount.AddDiscountDto;
import edu.raijin.finance.salary.infrastructure.adapter.in.rest.dto.discount.DiscountDto;
import edu.raijin.finance.salary.infrastructure.adapter.in.rest.mapper.DiscountDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/employees/{employeeId}/discounts")
@RequiredArgsConstructor
public class DiscountController {

    private final FetchDiscountUseCase fetch;
    private final CreateDiscountUseCase create;
    private final UpdateDiscountUseCase update;
    private final DeleteDiscountUseCase delete;
    private final DiscountDtoMapper mapper;

    @GetMapping
    public Paged<DiscountDto> fetchPaged(@PathVariable UUID employeeId, Pageable pageable) {
        return fetch.fetch(employeeId, pageable).map(mapper::toDto);
    }

    @GetMapping("/{discountId}")
    public DiscountDto fetch(@PathVariable UUID employeeId, @PathVariable Long discountId) {
        Discount discount = fetch.fetch(employeeId, discountId);
        return mapper.toDto(discount);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public DiscountDto create(@PathVariable UUID employeeId, @RequestBody @Valid AddDiscountDto discount) {
        Discount created = create.create(employeeId, mapper.toDomain(discount));
        return mapper.toDto(created);
    }

    @PutMapping("/{discountId}")
    public DiscountDto update(@PathVariable UUID employeeId, @PathVariable Long discountId,
            @RequestBody AddDiscountDto discount) {
        Discount updated = update.update(employeeId, discountId, mapper.toDomain(discount));
        return mapper.toDto(updated);
    }

    @DeleteMapping("/{discountId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable UUID employeeId, @PathVariable Long discountId) {
        delete.delete(employeeId, discountId);
    }
}
