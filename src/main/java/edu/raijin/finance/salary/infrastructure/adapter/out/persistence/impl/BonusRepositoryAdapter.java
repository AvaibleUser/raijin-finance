package edu.raijin.finance.salary.infrastructure.adapter.out.persistence.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.entity.EmployeesEntity;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.repository.JpaContractRepository;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.repository.JpaEmployeeRepository;
import edu.raijin.finance.salary.domain.model.Bonus;
import edu.raijin.finance.salary.domain.port.persistence.FindBonusPort;
import edu.raijin.finance.salary.domain.port.persistence.RegisterBonusPort;
import edu.raijin.finance.salary.domain.port.persistence.UpdateBonusPort;
import edu.raijin.finance.salary.infrastructure.adapter.out.persistence.entity.BonusesEntity;
import edu.raijin.finance.salary.infrastructure.adapter.out.persistence.mapper.BonusEntityMapper;
import edu.raijin.finance.salary.infrastructure.adapter.out.persistence.repository.JpaBonusRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class BonusRepositoryAdapter implements FindBonusPort, RegisterBonusPort, UpdateBonusPort {

    private final JpaBonusRepository bonusRepository;
    private final JpaEmployeeRepository employeeRepository;
    private final JpaContractRepository contractRepository;
    private final BonusEntityMapper mapper;

    @Override
    public Bonus register(UUID employeeId, Bonus bonus) {
        EmployeesEntity employee = employeeRepository.findById(employeeId).get();
        BonusesEntity entity = mapper.toEntity(bonus).withEmployee(employee);
        return mapper.toDomain(bonusRepository.save(entity));
    }

    @Override
    public Bonus update(Bonus bonus) {
        BonusesEntity entity = mapper.toEntity(bonus);
        return mapper.toDomain(bonusRepository.save(entity));
    }

    @Override
    public Optional<Bonus> findByIdAndEmployeeId(Long id, UUID employeeId) {
        return bonusRepository.findByIdAndEmployeeIdAndDeletedFalse(id, employeeId)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsContractedEmployee(UUID employeeId) {
        return contractRepository.existsEmployeeCurrentContract(employeeId);
    }

    @Override
    public Paged<Bonus> fetchAll(UUID employeeId, Pageable pageable) {
        Page<BonusesEntity> page = bonusRepository.findByEmployeeIdAndDeletedFalse(employeeId, pageable);
        return Paged.from(page.map(mapper::toDomain));
    }
}
