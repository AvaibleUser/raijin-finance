package edu.raijin.finance.project.infrastructure.adapter.in.messaging.topic;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.ProjectEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.project.domain.model.Project;
import edu.raijin.finance.project.domain.usecase.CreateProjectUseCase;
import edu.raijin.finance.project.infrastructure.adapter.in.messaging.mapper.ProjectEventMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class ProjectKafkaConsumerAdapter {

    private final CreateProjectUseCase createProject;
    private final ProjectEventMapper mapper;

    @KafkaListener(topics = "${kafka.topics.project-commands.topic}", id = "create", properties = "${kafka.topics.project-commands.default-value}")
    public void consumeCreatedProject(ProjectEvent event) {
        Project project = mapper.toDomain(event);
        createProject.create(project);
    }
}