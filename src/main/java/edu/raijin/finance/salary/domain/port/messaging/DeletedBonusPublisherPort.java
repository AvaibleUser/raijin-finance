package edu.raijin.finance.salary.domain.port.messaging;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.salary.domain.model.Bonus;

@Port
public interface DeletedBonusPublisherPort {

    void publishDeletedBonus(Bonus bonus);
}
