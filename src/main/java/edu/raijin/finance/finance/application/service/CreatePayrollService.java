package edu.raijin.finance.finance.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.finance.domain.model.Payroll;
import edu.raijin.finance.finance.domain.port.persistence.RegisterPayrollPort;
import edu.raijin.finance.finance.domain.usecase.CreatePayrollUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreatePayrollService implements CreatePayrollUseCase {

    private final RegisterPayrollPort register;

    @Override
    @Transactional
    public Payroll create(UUID employeeId, Payroll payroll) {
        if (!register.existsEmployee(employeeId)) {
            throw new ValueNotFoundException("El proyecto no se encuentra registrado");
        }
        payroll.checkValidRegistration();
        return register.register(employeeId, payroll);
    }
}
