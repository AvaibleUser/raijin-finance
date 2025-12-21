package edu.raijin.finance.project.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.project.domain.model.Project;

@UseCase
public interface UpdateProjectUseCase {

    Project update(UUID projectId, Project project);
}