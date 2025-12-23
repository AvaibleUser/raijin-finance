package edu.raijin.finance.salary.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.finance.salary.domain.model.Suspension;
import edu.raijin.finance.salary.domain.port.persistence.UpdateSuspensionPort;
import edu.raijin.finance.salary.domain.usecase.DeleteSuspensionUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteSuspensionService implements DeleteSuspensionUseCase {

    private final UpdateSuspensionPort update;

    @Override
    @Transactional
    public void delete(UUID employeeId, Long suspensionId) {
        Suspension suspension = update.findByIdAndEmployeeId(suspensionId, employeeId).orElse(null);
        if (suspension == null) {
            return;
        }

        suspension.delete();
        update.update(suspension);
    }
}
