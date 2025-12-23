package edu.raijin.finance.finance.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.finance.domain.model.Income;
import edu.raijin.finance.finance.domain.port.persistence.RegisterIncomePort;
import edu.raijin.finance.finance.domain.usecase.CreateIncomeUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateIncomeService implements CreateIncomeUseCase {

    private final RegisterIncomePort register;

    @Override
    @Transactional
    public Income create(UUID projectId, Income income) {
        if (!register.existsProject(projectId)) {
            throw new ValueNotFoundException("El proyecto no se encuentra registrado");
        }
        income.checkValidRegistration();
        return register.register(projectId, income);
    }
}
