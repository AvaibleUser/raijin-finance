package edu.raijin.finance.salary.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.finance.salary.domain.model.Bonus;
import edu.raijin.finance.salary.domain.port.persistence.UpdateBonusPort;
import edu.raijin.finance.salary.domain.usecase.DeleteBonusUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteBonusService implements DeleteBonusUseCase {

    private final UpdateBonusPort update;

    @Override
    @Transactional
    public void delete(UUID employeeId, Long bonusId) {
        Bonus bonus = update.findByIdAndEmployeeId(bonusId, employeeId).orElse(null);
        if (bonus == null) {
            return;
        }

        bonus.delete();
        update.update(bonus);
    }
}
