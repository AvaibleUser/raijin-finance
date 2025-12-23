package edu.raijin.finance.salary.application.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.salary.domain.model.Bonus;
import edu.raijin.finance.salary.domain.port.persistence.FindBonusPort;
import edu.raijin.finance.salary.domain.usecase.FetchBonusUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchBonusService implements FetchBonusUseCase {

    private final FindBonusPort find;

    @Override
    @Transactional
    public Bonus fetch(UUID employeeId, Long bonusId) {
        return find.findByIdAndEmployeeId(bonusId, employeeId)
                .orElseThrow(() -> new ValueNotFoundException("El bono no se encuentra registrado"));
    }

    @Override
    public Paged<Bonus> fetch(UUID employeeId, Pageable pageable) {
        return find.fetchAll(employeeId, pageable);
    }
}