package edu.raijin.finance.salary.application.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.salary.domain.model.Suspension;
import edu.raijin.finance.salary.domain.port.persistence.FindSuspensionPort;
import edu.raijin.finance.salary.domain.usecase.FetchSuspensionUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchSuspensionService implements FetchSuspensionUseCase {

    private final FindSuspensionPort find;

    @Override
    @Transactional
    public Suspension fetch(UUID employeeId, Long suspensionId) {
        return find.findByIdAndEmployeeId(suspensionId, employeeId)
                .orElseThrow(() -> new ValueNotFoundException("La suspensión no se encuentra registrada"));
    }

    @Override
    public Paged<Suspension> fetch(UUID employeeId, Pageable pageable) {
        return find.fetchAll(employeeId, pageable);
    }
}