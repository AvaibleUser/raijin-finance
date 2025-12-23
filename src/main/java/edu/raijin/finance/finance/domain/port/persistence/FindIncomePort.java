package edu.raijin.finance.finance.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.finance.domain.model.Income;

@Port
public interface FindIncomePort {

    Optional<Income> findByIdAndProjectId(Long id, UUID projectId);

    Paged<Income> fetchAll(UUID projectId, Pageable pageable);
}
