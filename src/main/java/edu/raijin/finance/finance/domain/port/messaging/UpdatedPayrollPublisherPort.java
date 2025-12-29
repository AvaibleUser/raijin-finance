package edu.raijin.finance.finance.domain.port.messaging;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.finance.domain.model.Payroll;

@Port
public interface UpdatedPayrollPublisherPort {

    void publishUpdatedPayroll(Payroll payroll);
}
