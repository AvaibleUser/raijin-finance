package edu.raijin.finance.project.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.raijin.finance.project.domain.model.Project;
import edu.raijin.finance.project.domain.port.persistence.UpdateProjectPort;
import edu.raijin.finance.project.domain.usecase.DeleteProjectUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteProjectService implements DeleteProjectUseCase {

    private final UpdateProjectPort update;

    @Override
    @Transactional
    public void delete(UUID projectId) {
        Project project = update.findById(projectId).orElse(null);
        if (project == null) {
            return;
        }

        project.delete();
        update.update(project);
    }
}