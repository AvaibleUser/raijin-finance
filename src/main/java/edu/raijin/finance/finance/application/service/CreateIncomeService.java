package edu.raijin.finance.finance.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.finance.domain.model.Income;
import edu.raijin.finance.finance.domain.port.messaging.CreatedIncomePublisherPort;
import edu.raijin.finance.finance.domain.port.persistence.RegisterIncomePort;
import edu.raijin.finance.finance.domain.usecase.CreateIncomeUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateIncomeService implements CreateIncomeUseCase {

    private final RegisterIncomePort register;
    private final CreatedIncomePublisherPort publisher;

    @Override
    @Transactional
    public Income create(UUID projectId, Income income) {
        if (!register.existsProject(projectId)) {
            throw new ValueNotFoundException("El proyecto no se encuentra registrado");
        }
        income.checkValidRegistration();
        Income created = register.register(projectId, income);

        publisher.publishCreatedIncome(created);
        return created;
    }
}
