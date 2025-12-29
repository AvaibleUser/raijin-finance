package edu.raijin.finance.finance.domain.port.messaging;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.finance.domain.model.Payroll;

@Port
public interface CreatedPayrollPublisherPort {

    void publishCreatedPayroll(Payroll payroll);
}
