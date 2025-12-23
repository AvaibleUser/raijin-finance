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
import edu.raijin.finance.salary.domain.model.Discount;
import edu.raijin.finance.salary.domain.port.persistence.FindDiscountPort;
import edu.raijin.finance.salary.domain.port.persistence.RegisterDiscountPort;
import edu.raijin.finance.salary.domain.port.persistence.UpdateDiscountPort;
import edu.raijin.finance.salary.infrastructure.adapter.out.persistence.entity.DiscountsEntity;
import edu.raijin.finance.salary.infrastructure.adapter.out.persistence.mapper.DiscountEntityMapper;
import edu.raijin.finance.salary.infrastructure.adapter.out.persistence.repository.JpaDiscountRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class DiscountRepositoryAdapter implements FindDiscountPort, RegisterDiscountPort, UpdateDiscountPort {

    private final JpaDiscountRepository discountRepository;
    private final JpaEmployeeRepository employeeRepository;
    private final JpaContractRepository contractRepository;
    private final DiscountEntityMapper mapper;

    @Override
    public Discount register(UUID employeeId, Discount discount) {
        EmployeesEntity employee = employeeRepository.findById(employeeId).get();
        DiscountsEntity entity = mapper.toEntity(discount).withEmployee(employee);
        return mapper.toDomain(discountRepository.save(entity));
    }

    @Override
    public Discount update(Discount discount) {
        DiscountsEntity entity = mapper.toEntity(discount);
        return mapper.toDomain(discountRepository.save(entity));
    }

    @Override
    public Optional<Discount> findByIdAndEmployeeId(Long id, UUID employeeId) {
        return discountRepository.findByIdAndEmployeeIdAndDeletedFalse(id, employeeId)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsContractedEmployee(UUID employeeId) {
        return contractRepository.existsEmployeeCurrentContract(employeeId);
    }

    @Override
    public Paged<Discount> fetchAll(UUID employeeId, Pageable pageable) {
        Page<DiscountsEntity> page = discountRepository.findByEmployeeIdAndDeletedFalse(employeeId, pageable);
        return Paged.from(page.map(mapper::toDomain));
    }
}
