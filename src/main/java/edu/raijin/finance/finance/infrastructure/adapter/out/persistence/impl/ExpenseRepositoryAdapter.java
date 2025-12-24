package edu.raijin.finance.finance.infrastructure.adapter.out.persistence.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.repository.JpaEmployeeRepository;
import edu.raijin.finance.finance.domain.model.Expense;
import edu.raijin.finance.finance.domain.port.persistence.FindExpensePort;
import edu.raijin.finance.finance.domain.port.persistence.RegisterExpensePort;
import edu.raijin.finance.finance.domain.port.persistence.UpdateExpensePort;
import edu.raijin.finance.finance.infrastructure.adapter.out.persistence.entity.ExpensesEntity;
import edu.raijin.finance.finance.infrastructure.adapter.out.persistence.mapper.ExpenseEntityMapper;
import edu.raijin.finance.finance.infrastructure.adapter.out.persistence.repository.JpaExpenseRepository;
import edu.raijin.finance.project.infrastructure.adapter.out.persistence.repository.JpaProjectRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class ExpenseRepositoryAdapter implements FindExpensePort, RegisterExpensePort, UpdateExpensePort {

    private final JpaExpenseRepository expenseRepository;
    private final JpaProjectRepository projectRepository;
    private final JpaEmployeeRepository employeeRepository;
    private final ExpenseEntityMapper mapper;

    @Override
    public Expense register(UUID projectId, UUID employeeId, Expense expense) {
        ExpensesEntity entity = mapper.toEntity(expense)
                .toBuilder()
                .project(Optional.ofNullable(projectId)
                        .flatMap(projectRepository::findById)
                        .orElse(null))
                .employee(Optional.ofNullable(employeeId)
                        .flatMap(employeeRepository::findById)
                        .orElse(null))
                .build();

        return mapper.toDomain(expenseRepository.save(entity));
    }

    @Override
    public Expense update(Expense expense) {
        ExpensesEntity entity = mapper.toEntity(expense);
        if (entity.getProject().getId() == null) {
            entity.setProject(null);
        }
        if (entity.getEmployee().getId() == null) {
            entity.setEmployee(null);
        }
        return mapper.toDomain(expenseRepository.save(entity));
    }

    @Override
    public Optional<Expense> findByIdAndProjectId(Long id, UUID projectId) {
        return expenseRepository.findByIdAndProjectIdAndDeletedFalse(id, projectId)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsEmployee(UUID employeeId) {
        return employeeRepository.existsByIdAndDeletedFalse(employeeId);
    }

    @Override
    public boolean existsProject(UUID projectId) {
        return projectRepository.existsByIdAndDeletedFalse(projectId);
    }

    @Override
    public Paged<Expense> fetchAllByProject(UUID projectId, Pageable pageable) {
        Page<ExpensesEntity> page = expenseRepository.findByProjectIdAndDeletedFalse(projectId, pageable);
        return Paged.from(page.map(mapper::toDomain));
    }
}
