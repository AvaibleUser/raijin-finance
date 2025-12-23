package edu.raijin.finance.finance.application.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.finance.domain.model.Income;
import edu.raijin.finance.finance.domain.port.persistence.FindIncomePort;
import edu.raijin.finance.finance.domain.usecase.FetchIncomeUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchIncomeService implements FetchIncomeUseCase {

    private final FindIncomePort find;

    @Override
    @Transactional
    public Income fetch(UUID projectId, Long incomeId) {
        return find.findByIdAndProjectId(incomeId, projectId)
                .orElseThrow(() -> new ValueNotFoundException("El ingreso no se encuentra registrado"));
    }

    @Override
    public Paged<Income> fetch(UUID projectId, Pageable pageable) {
        return find.fetchAll(projectId, pageable);
    }
}