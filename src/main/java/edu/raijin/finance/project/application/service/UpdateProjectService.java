package edu.raijin.finance.project.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.project.domain.model.Project;
import edu.raijin.finance.project.domain.port.persistence.UpdateProjectPort;
import edu.raijin.finance.project.domain.usecase.UpdateProjectUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateProjectService implements UpdateProjectUseCase {

    private final UpdateProjectPort update;

    @Override
    @Transactional
    public Project update(UUID projectId, Project updated) {
        Project project = update.findById(projectId)
                .orElseThrow(() -> new ValueNotFoundException("El proyecto no existe"));

        project.updateFrom(updated);
        project.checkValidRegistration();
        return update.update(project);
    }
}