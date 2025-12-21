package edu.raijin.finance.project.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.finance.project.domain.model.Project;
import edu.raijin.finance.project.domain.port.persistence.RegisterProjectPort;
import edu.raijin.finance.project.domain.usecase.CreateProjectUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateProjectService implements CreateProjectUseCase {

    private final RegisterProjectPort register;

    @Override
    @Transactional
    public UUID create(Project project) {
        project.checkValidRegistration();
        return register.create(project);
    }
}