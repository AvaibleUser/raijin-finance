package edu.raijin.finance.finance.infrastructure.adapter.out.persistence.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.finance.domain.model.Income;
import edu.raijin.finance.finance.domain.port.persistence.FindIncomePort;
import edu.raijin.finance.finance.domain.port.persistence.RegisterIncomePort;
import edu.raijin.finance.finance.domain.port.persistence.UpdateIncomePort;
import edu.raijin.finance.finance.infrastructure.adapter.out.persistence.entity.IncomesEntity;
import edu.raijin.finance.finance.infrastructure.adapter.out.persistence.mapper.IncomeEntityMapper;
import edu.raijin.finance.finance.infrastructure.adapter.out.persistence.repository.JpaIncomeRepository;
import edu.raijin.finance.project.infrastructure.adapter.out.persistence.entity.ProjectsEntity;
import edu.raijin.finance.project.infrastructure.adapter.out.persistence.repository.JpaProjectRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class IncomeRepositoryAdapter implements FindIncomePort, RegisterIncomePort, UpdateIncomePort {

    private final JpaIncomeRepository incomeRepository;
    private final JpaProjectRepository projectRepository;
    private final IncomeEntityMapper mapper;

    @Override
    public Income register(UUID projectId, Income income) {
        ProjectsEntity project = projectRepository.findById(projectId).get();
        IncomesEntity entity = mapper.toEntity(income).withProject(project);
        return mapper.toDomain(incomeRepository.save(entity));
    }

    @Override
    public Income update(Income income) {
        IncomesEntity entity = mapper.toEntity(income);
        return mapper.toDomain(incomeRepository.save(entity));
    }

    @Override
    public Optional<Income> findByIdAndProjectId(Long id, UUID projectId) {
        return incomeRepository.findByIdAndProjectIdAndDeletedFalse(id, projectId)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsProject(UUID projectId) {
        return projectRepository.existsByIdAndDeletedFalse(projectId);
    }

    @Override
    public Paged<Income> fetchAll(UUID projectId, Pageable pageable) {
        Page<IncomesEntity> page = incomeRepository.findByProjectIdAndDeletedFalse(projectId, pageable);
        return Paged.from(page.map(mapper::toDomain));
    }
}
