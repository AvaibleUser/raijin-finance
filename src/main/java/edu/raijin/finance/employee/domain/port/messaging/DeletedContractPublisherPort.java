package edu.raijin.finance.employee.domain.port.messaging;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.employee.domain.model.Contract;

@Port
public interface DeletedContractPublisherPort {

    void publishDeletedContract(Contract contract);
}
