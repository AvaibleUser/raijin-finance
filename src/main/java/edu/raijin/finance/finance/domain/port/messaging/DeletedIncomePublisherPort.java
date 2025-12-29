package edu.raijin.finance.finance.domain.port.messaging;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.finance.domain.model.Income;

@Port
public interface DeletedIncomePublisherPort {

    void publishDeletedIncome(Income income);
}
