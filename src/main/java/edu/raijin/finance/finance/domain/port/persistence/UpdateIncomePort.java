package edu.raijin.finance.finance.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.finance.domain.model.Income;

@Port
public interface UpdateIncomePort {

    Optional<Income> findByIdAndProjectId(Long id, UUID projectId);

    Income update(Income income);
}
