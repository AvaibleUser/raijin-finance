package edu.raijin.finance.salary.domain.usecase;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.salary.domain.model.Discount;

@UseCase
public interface FetchDiscountUseCase {

    Discount fetch(UUID employeeId, Long discountId);

    Paged<Discount> fetch(UUID employeeId, Pageable pageable);
}
