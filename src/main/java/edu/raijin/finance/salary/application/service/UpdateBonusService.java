package edu.raijin.finance.salary.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.salary.domain.model.Bonus;
import edu.raijin.finance.salary.domain.port.persistence.UpdateBonusPort;
import edu.raijin.finance.salary.domain.usecase.UpdateBonusUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateBonusService implements UpdateBonusUseCase {

    private final UpdateBonusPort update;

    @Override
    @Transactional
    public Bonus update(UUID employeeId, Long bonusId, Bonus patches) {
        Bonus bonus = update.findByIdAndEmployeeId(bonusId, employeeId)
                .orElseThrow(() -> new ValueNotFoundException("El bono no se encuentra registrado"));

        bonus.updateFrom(patches);
        bonus.checkValidRegistration();

        return update.update(bonus);
    }
}