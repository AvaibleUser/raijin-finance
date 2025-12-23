package edu.raijin.finance.salary.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.salary.domain.model.Discount;

@UseCase
public interface UpdateDiscountUseCase {

    Discount update(UUID employeeId, Long discountId, Discount discount);
}
