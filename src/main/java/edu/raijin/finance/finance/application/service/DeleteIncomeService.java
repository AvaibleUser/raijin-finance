package edu.raijin.finance.finance.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.finance.finance.domain.model.Income;
import edu.raijin.finance.finance.domain.port.persistence.UpdateIncomePort;
import edu.raijin.finance.finance.domain.usecase.DeleteIncomeUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteIncomeService implements DeleteIncomeUseCase {

    private final UpdateIncomePort update;

    @Override
    @Transactional
    public void delete(UUID projectId, Long incomeId) {
        Income income = update.findByIdAndProjectId(incomeId, projectId).orElse(null);
        if (income == null) {
            return;
        }

        income.delete();
        update.update(income);
    }
}
