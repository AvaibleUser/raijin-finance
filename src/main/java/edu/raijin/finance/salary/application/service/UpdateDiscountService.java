package edu.raijin.finance.salary.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.salary.domain.model.Discount;
import edu.raijin.finance.salary.domain.port.messaging.UpdatedDiscountPublisherPort;
import edu.raijin.finance.salary.domain.port.persistence.UpdateDiscountPort;
import edu.raijin.finance.salary.domain.usecase.UpdateDiscountUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateDiscountService implements UpdateDiscountUseCase {

    private final UpdateDiscountPort update;
    private final UpdatedDiscountPublisherPort publisher;

    @Override
    @Transactional
    public Discount update(UUID employeeId, Long discountId, Discount patches) {
        Discount discount = update.findByIdAndEmployeeId(discountId, employeeId)
                .orElseThrow(() -> new ValueNotFoundException("El descuento no se encuentra registrado"));

        Discount diff = discount.diff(patches);
        discount.updateFrom(patches);
        discount.checkValidRegistration();

        Discount updated = update.update(discount);
        publisher.publishUpdatedDiscount(diff);
        return updated;
    }
}