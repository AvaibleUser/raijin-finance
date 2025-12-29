package edu.raijin.finance.salary.domain.port.messaging;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.salary.domain.model.Suspension;

@Port
public interface CreatedSuspensionPublisherPort {

    void publishCreatedSuspension(Suspension suspension);
}
