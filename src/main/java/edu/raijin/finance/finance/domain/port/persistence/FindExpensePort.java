package edu.raijin.finance.finance.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.finance.domain.model.Expense;

@Port
public interface FindExpensePort {

    Optional<Expense> findByIdAndProjectId(Long id, UUID projectId);

    Paged<Expense> fetchAllByProject(UUID projectId, Pageable pageable);
}
