package edu.raijin.finance.finance.domain.usecase;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.finance.domain.model.Income;

@UseCase
public interface FetchIncomeUseCase {

    Income fetch(UUID projectId, Long incomeId);

    Paged<Income> fetch(UUID projectId, Pageable pageable);
}
