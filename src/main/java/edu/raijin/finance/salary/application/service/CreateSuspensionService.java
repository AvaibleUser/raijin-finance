package edu.raijin.finance.salary.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.salary.domain.model.Suspension;
import edu.raijin.finance.salary.domain.port.messaging.CreatedSuspensionPublisherPort;
import edu.raijin.finance.salary.domain.port.persistence.RegisterSuspensionPort;
import edu.raijin.finance.salary.domain.usecase.CreateSuspensionUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateSuspensionService implements CreateSuspensionUseCase {

    private final RegisterSuspensionPort register;
    private final CreatedSuspensionPublisherPort publisher;

    @Override
    @Transactional
    public Suspension create(UUID employeeId, Suspension suspension) {
        if (!register.existsContractedEmployee(employeeId)) {
            throw new ValueNotFoundException("El empleado no se encuentra registrado");
        }
        suspension.checkValidRegistration();
        Suspension created = register.register(employeeId, suspension);

        publisher.publishCreatedSuspension(created);
        return created;
    }
}
