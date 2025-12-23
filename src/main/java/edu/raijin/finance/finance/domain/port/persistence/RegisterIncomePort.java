package edu.raijin.finance.finance.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.finance.domain.model.Income;

@Port
public interface RegisterIncomePort {

    boolean existsProject(UUID projectId);

    Income register(UUID projectId, Income income);
}
