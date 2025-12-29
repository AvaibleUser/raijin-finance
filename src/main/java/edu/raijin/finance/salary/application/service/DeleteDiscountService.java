package edu.raijin.finance.salary.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.finance.salary.domain.model.Discount;
import edu.raijin.finance.salary.domain.port.messaging.DeletedDiscountPublisherPort;
import edu.raijin.finance.salary.domain.port.persistence.UpdateDiscountPort;
import edu.raijin.finance.salary.domain.usecase.DeleteDiscountUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteDiscountService implements DeleteDiscountUseCase {

    private final UpdateDiscountPort update;
    private final DeletedDiscountPublisherPort publisher;

    @Override
    @Transactional
    public void delete(UUID employeeId, Long discountId) {
        Discount discount = update.findByIdAndEmployeeId(discountId, employeeId).orElse(null);
        if (discount == null) {
            return;
        }

        Discount deleted = discount.deleted();
        discount.delete();
        update.update(discount);

        publisher.publishDeletedDiscount(deleted);
    }
}
