package edu.raijin.finance.finance.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.finance.domain.model.Income;
import edu.raijin.finance.finance.domain.port.messaging.UpdatedIncomePublisherPort;
import edu.raijin.finance.finance.domain.port.persistence.UpdateIncomePort;
import edu.raijin.finance.finance.domain.usecase.UpdateIncomeUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateIncomeService implements UpdateIncomeUseCase {

    private final UpdateIncomePort update;
    private final UpdatedIncomePublisherPort publisher;

    @Override
    @Transactional
    public Income update(UUID projectId, Long incomeId, Income patches) {
        Income income = update.findByIdAndProjectId(incomeId, projectId)
                .orElseThrow(() -> new ValueNotFoundException("El bono no se encuentra registrado"));

        Income diff = income.diff(patches);
        income.updateFrom(patches);
        income.checkValidRegistration();

        Income updated = update.update(income);

        publisher.publishUpdatedIncome(diff);
        return updated;
    }
}