package edu.raijin.finance.project.infrastructure.adapter.out.persistence.impl;

import java.util.Optional;
import java.util.UUID;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.entity.EmployeesEntity;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.repository.JpaEmployeeRepository;
import edu.raijin.finance.project.domain.model.Project;
import edu.raijin.finance.project.domain.port.persistence.AssignMemberPort;
import edu.raijin.finance.project.domain.port.persistence.RegisterProjectPort;
import edu.raijin.finance.project.domain.port.persistence.UnassignMemberPort;
import edu.raijin.finance.project.domain.port.persistence.UpdateProjectPort;
import edu.raijin.finance.project.infrastructure.adapter.out.persistence.entity.ProjectsEntity;
import edu.raijin.finance.project.infrastructure.adapter.out.persistence.mapper.ProjectEntityMapper;
import edu.raijin.finance.project.infrastructure.adapter.out.persistence.repository.JpaProjectRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class ProjectRepositoryAdapter
        implements RegisterProjectPort, UpdateProjectPort, AssignMemberPort, UnassignMemberPort {

    private final JpaProjectRepository projectRepository;
    private final JpaEmployeeRepository employeeRepository;
    private final ProjectEntityMapper mapper;

    @Override
    public UUID create(Project project) {
        ProjectsEntity entity = mapper.toEntity(project);
        return projectRepository.save(entity).getId();
    }

    @Override
    public Project update(Project project) {
        ProjectsEntity entity = mapper.toEntity(project);
        return mapper.toDomain(projectRepository.save(entity));
    }

    @Override
    public Optional<Project> findById(UUID id) {
        return projectRepository.findByIdAndDeletedFalse(id)
                .map(mapper::toDomain);
    }

    @Override
    public boolean exists(UUID projectId, UUID employeeId) {
        return projectRepository.existsMember(projectId, employeeId);
    }

    @Override
    public void unassign(UUID projectId, UUID employeeId) {
        ProjectsEntity project = projectRepository.findById(projectId).get();
        EmployeesEntity user = employeeRepository.findById(employeeId).get();

        Hibernate.initialize(project.getMembers());
        project.getMembers().add(user);

        projectRepository.save(project);
    }

    @Override
    public void assign(UUID projectId, UUID employeeId) {
        ProjectsEntity project = projectRepository.findById(projectId).get();
        EmployeesEntity user = employeeRepository.findById(employeeId).get();

        Hibernate.initialize(project.getMembers());
        project.getMembers().remove(user);

        projectRepository.save(project);
    }
}
