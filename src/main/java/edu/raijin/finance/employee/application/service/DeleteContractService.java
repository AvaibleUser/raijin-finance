package edu.raijin.finance.employee.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.finance.employee.domain.model.Contract;
import edu.raijin.finance.employee.domain.port.messaging.DeletedContractPublisherPort;
import edu.raijin.finance.employee.domain.port.persistence.UpdateContractPort;
import edu.raijin.finance.employee.domain.usecase.DeleteContractUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteContractService implements DeleteContractUseCase {

    private final UpdateContractPort update;
    private final DeletedContractPublisherPort publisher;

    @Override
    @Transactional
    public void delete(UUID employeeId, Long contractId) {
        Contract contract = update.findByIdAndEmployeeId(contractId, employeeId).orElse(null);
        if (contract == null) {
            return;
        }

        contract.delete();
        update.update(contract);

        publisher.publishDeletedContract(contract);
    }
}