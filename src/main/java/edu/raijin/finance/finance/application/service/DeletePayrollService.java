package edu.raijin.finance.finance.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.finance.finance.domain.model.Payroll;
import edu.raijin.finance.finance.domain.port.persistence.UpdatePayrollPort;
import edu.raijin.finance.finance.domain.usecase.DeletePayrollUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeletePayrollService implements DeletePayrollUseCase {

    private final UpdatePayrollPort update;

    @Override
    @Transactional
    public void delete(UUID employeeId, Long payrollId) {
        Payroll payroll = update.findByIdAndEmployeeId(payrollId, employeeId).orElse(null);
        if (payroll == null) {
            return;
        }

        payroll.delete();
        update.update(payroll);
    }
}
