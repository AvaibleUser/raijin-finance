package edu.raijin.finance.employee.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.domain.type.ContractStatus;
import edu.raijin.commons.util.exception.RequestConflictException;
import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.employee.domain.model.Contract;
import edu.raijin.finance.employee.domain.port.messaging.UpdatedContractPublisherPort;
import edu.raijin.finance.employee.domain.port.persistence.UpdateContractPort;
import edu.raijin.finance.employee.domain.usecase.UpdateContractUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateContractService implements UpdateContractUseCase {

    private final UpdateContractPort update;
    private final UpdatedContractPublisherPort publisher;

    @Override
    @Transactional
    public Contract update(UUID employeeId, UUID contractId, Contract patches) {
        Contract contract = update.findByIdAndEmployeeId(contractId, employeeId)
                .orElseThrow(() -> new ValueNotFoundException("El contrato no se encuentra registrado"));

        if (patches.getStatus() == ContractStatus.ACTIVE
                && update.existsAnotherCurrentByEmployeeId(contractId, employeeId)) {
            throw new RequestConflictException("El empleado ya tiene un contrato vigente");
        }
        contract.updateFrom(patches);
        contract.checkValidRegistration();

        Contract updated = update.update(contract);

        publisher.publishUpdatedContract(updated);
        return updated;
    }
}