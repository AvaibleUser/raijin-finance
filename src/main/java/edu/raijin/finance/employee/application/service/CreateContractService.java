package edu.raijin.finance.employee.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.domain.type.ContractStatus;
import edu.raijin.commons.util.exception.RequestConflictException;
import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.employee.domain.model.Contract;
import edu.raijin.finance.employee.domain.port.messaging.CreatedContractPublisherPort;
import edu.raijin.finance.employee.domain.port.persistence.RegisterContractPort;
import edu.raijin.finance.employee.domain.usecase.CreateContractUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateContractService implements CreateContractUseCase {

    private final RegisterContractPort register;
    private final CreatedContractPublisherPort publisher;

    @Override
    @Transactional
    public Contract create(UUID employeeId, Contract contract) {
        if (!register.existsEmployee(employeeId)) {
            throw new ValueNotFoundException("El empleado no se encuentra registrado");
        }
        if (contract.getStatus() == ContractStatus.ACTIVE && register.existsCurrentByEmployeeId(employeeId)) {
            throw new RequestConflictException("El empleado ya tiene un contrato vigente");
        }
        contract.checkValidRegistration();
        Contract created = register.register(employeeId, contract);

        publisher.publishCreatedContract(created);
        return created;
    }
}
