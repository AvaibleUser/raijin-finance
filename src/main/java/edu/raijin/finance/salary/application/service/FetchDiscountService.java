package edu.raijin.finance.salary.application.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.salary.domain.model.Discount;
import edu.raijin.finance.salary.domain.port.persistence.FindDiscountPort;
import edu.raijin.finance.salary.domain.usecase.FetchDiscountUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchDiscountService implements FetchDiscountUseCase {

    private final FindDiscountPort find;

    @Override
    @Transactional
    public Discount fetch(UUID employeeId, Long discountId) {
        return find.findByIdAndEmployeeId(discountId, employeeId)
                .orElseThrow(() -> new ValueNotFoundException("El descuento no se encuentra registrado"));
    }

    @Override
    public Paged<Discount> fetch(UUID employeeId, Pageable pageable) {
        return find.fetchAll(employeeId, pageable);
    }
}