package edu.raijin.finance.finance.application.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.finance.domain.model.Payroll;
import edu.raijin.finance.finance.domain.port.persistence.FindPayrollPort;
import edu.raijin.finance.finance.domain.usecase.FetchPayrollUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchPayrollService implements FetchPayrollUseCase {

    private final FindPayrollPort find;

    @Override
    @Transactional
    public Payroll fetch(UUID employeeId, Long payrollId) {
        return find.findByIdAndEmployeeId(payrollId, employeeId)
                .orElseThrow(() -> new ValueNotFoundException("El ingreso no se encuentra registrado"));
    }

    @Override
    public Paged<Payroll> fetch(UUID employeeId, Pageable pageable) {
        return find.fetchAll(employeeId, pageable);
    }
}