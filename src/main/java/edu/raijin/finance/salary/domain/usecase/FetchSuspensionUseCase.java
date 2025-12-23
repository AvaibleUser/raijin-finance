package edu.raijin.finance.salary.domain.usecase;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.salary.domain.model.Suspension;

@UseCase
public interface FetchSuspensionUseCase {

    Suspension fetch(UUID employeeId, Long suspensionId);

    Paged<Suspension> fetch(UUID employeeId, Pageable pageable);
}
