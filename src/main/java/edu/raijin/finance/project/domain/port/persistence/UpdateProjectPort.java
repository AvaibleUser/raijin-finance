package edu.raijin.finance.project.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.finance.project.domain.model.Project;

@Port
public interface UpdateProjectPort {

    Optional<Project> findById(UUID id);

    Project update(Project project);
}