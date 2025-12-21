package edu.raijin.finance.project.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.project.domain.model.Project;

@UseCase
public interface CreateProjectUseCase {

    UUID create(Project project);
}
