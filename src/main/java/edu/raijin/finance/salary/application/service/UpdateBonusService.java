package edu.raijin.finance.salary.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.salary.domain.model.Bonus;
import edu.raijin.finance.salary.domain.port.messaging.UpdatedBonusPublisherPort;
import edu.raijin.finance.salary.domain.port.persistence.UpdateBonusPort;
import edu.raijin.finance.salary.domain.usecase.UpdateBonusUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateBonusService implements UpdateBonusUseCase {

    private final UpdateBonusPort update;
    private final UpdatedBonusPublisherPort publisher;

    @Override
    @Transactional
    public Bonus update(UUID employeeId, Long bonusId, Bonus patches) {
        Bonus bonus = update.findByIdAndEmployeeId(bonusId, employeeId)
                .orElseThrow(() -> new ValueNotFoundException("El bono no se encuentra registrado"));

        Bonus diff = bonus.diff(patches);
        bonus.updateFrom(patches);
        bonus.checkValidRegistration();

        Bonus updated = update.update(bonus);
        publisher.publishUpdatedBonus(diff);
        return updated;
    }
}