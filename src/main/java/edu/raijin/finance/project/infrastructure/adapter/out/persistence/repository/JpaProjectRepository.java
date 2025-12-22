package edu.raijin.finance.project.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.finance.project.infrastructure.adapter.out.persistence.entity.ProjectsEntity;

@Repository
public interface JpaProjectRepository extends JpaRepository<ProjectsEntity, UUID> {

    Optional<ProjectsEntity> findByIdAndDeletedFalse(UUID id);

    boolean existsByIdAndDeletedFalse(UUID id);

    boolean existsByIdAndDeletedFalseAndMembersIdAndMembersDeletedFalse(UUID projectId, UUID userId);

    default boolean existsMember(UUID projectId, UUID userId) {
        return existsByIdAndDeletedFalseAndMembersIdAndMembersDeletedFalse(projectId, userId);
    }
}
