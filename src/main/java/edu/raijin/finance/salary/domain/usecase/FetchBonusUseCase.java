package edu.raijin.finance.salary.domain.usecase;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.salary.domain.model.Bonus;

@UseCase
public interface FetchBonusUseCase {

    Bonus fetch(UUID employeeId, Long bonusId);

    Paged<Bonus> fetch(UUID employeeId, Pageable pageable);
}
