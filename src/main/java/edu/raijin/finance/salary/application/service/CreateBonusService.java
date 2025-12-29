package edu.raijin.finance.salary.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.salary.domain.model.Bonus;
import edu.raijin.finance.salary.domain.port.messaging.CreatedBonusPublisherPort;
import edu.raijin.finance.salary.domain.port.persistence.RegisterBonusPort;
import edu.raijin.finance.salary.domain.usecase.CreateBonusUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateBonusService implements CreateBonusUseCase {

    private final RegisterBonusPort register;
    private final CreatedBonusPublisherPort publisher;

    @Override
    @Transactional
    public Bonus create(UUID employeeId, Bonus bonus) {
        if (!register.existsContractedEmployee(employeeId)) {
            throw new ValueNotFoundException("El empleado no se encuentra registrado");
        }
        bonus.checkValidRegistration();
        Bonus created = register.register(employeeId, bonus);

        publisher.publishCreatedBonus(created);
        return created;
    }
}
