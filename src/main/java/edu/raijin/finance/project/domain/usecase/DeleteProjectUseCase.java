package edu.raijin.finance.project.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;

@UseCase
public interface DeleteProjectUseCase {

    void delete(UUID projectId);
}