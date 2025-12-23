package edu.raijin.finance.salary.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.salary.domain.model.Discount;

@UseCase
public interface CreateDiscountUseCase {

    Discount create(UUID employeeId, Discount discount);
}