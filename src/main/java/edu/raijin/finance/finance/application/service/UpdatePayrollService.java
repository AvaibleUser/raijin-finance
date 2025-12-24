package edu.raijin.finance.finance.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.finance.domain.model.Payroll;
import edu.raijin.finance.finance.domain.port.persistence.UpdatePayrollPort;
import edu.raijin.finance.finance.domain.usecase.UpdatePayrollUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdatePayrollService implements UpdatePayrollUseCase {

    private final UpdatePayrollPort update;

    @Override
    @Transactional
    public Payroll update(UUID employeeId, Long payrollId, Payroll patches) {
        Payroll payroll = update.findByIdAndEmployeeId(payrollId, employeeId)
                .orElseThrow(() -> new ValueNotFoundException("El bono no se encuentra registrado"));

        payroll.updateFrom(patches);
        payroll.checkValidRegistration();

        return update.update(payroll);
    }
}