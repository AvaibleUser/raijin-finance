package edu.raijin.finance.salary.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.salary.domain.model.Suspension;
import edu.raijin.finance.salary.domain.port.persistence.UpdateSuspensionPort;
import edu.raijin.finance.salary.domain.usecase.UpdateSuspensionUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateSuspensionService implements UpdateSuspensionUseCase {

    private final UpdateSuspensionPort update;

    @Override
    @Transactional
    public Suspension update(UUID employeeId, Long suspensionId, Suspension patches) {
        Suspension suspension = update.findByIdAndEmployeeId(suspensionId, employeeId)
                .orElseThrow(() -> new ValueNotFoundException("La suspensión no se encuentra registrada"));

        suspension.updateFrom(patches);
        suspension.checkValidRegistration();

        return update.update(suspension);
    }
}