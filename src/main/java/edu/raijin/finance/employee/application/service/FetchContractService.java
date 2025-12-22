package edu.raijin.finance.employee.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.employee.domain.model.Contract;
import edu.raijin.finance.employee.domain.port.persistence.FindContractPort;
import edu.raijin.finance.employee.domain.usecase.FetchContractUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchContractService implements FetchContractUseCase {

    private final FindContractPort find;

    @Override
    @Transactional
    public Contract fetch(UUID employeeId, Long contractId) {
        return find.findByIdAndEmployeeId(contractId, employeeId)
                .orElseThrow(() -> new ValueNotFoundException("El contrato no se encuentra registrado"));
    }

    @Override
    @Transactional
    public Contract fetchEmployeeCurrentContract(UUID employeeId) {
        return find.findCurrentByEmployeeId(employeeId)
                .orElseThrow(() -> new ValueNotFoundException("El empleado no tiene un contrato vigente"));
    }
}