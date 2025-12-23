package edu.raijin.finance.finance.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.finance.domain.model.Income;

@UseCase
public interface CreateIncomeUseCase {

    Income create(UUID projectId, Income income);
}