package edu.raijin.finance.salary.domain.port.messaging;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.salary.domain.model.Discount;

@Port
public interface UpdatedDiscountPublisherPort {

    void publishUpdatedDiscount(Discount discount);
}
