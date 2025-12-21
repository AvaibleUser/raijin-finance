package edu.raijin.finance.project.infrastructure.adapter.in.messaging.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.domain.type.ProjectStatus;
import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.ProjectEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.project.domain.model.Project;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectEventMapper {

    @Mapping(target = "closed", source = "status")
    Project toDomain(ProjectEvent event);

    default Boolean mapStatus(ProjectStatus status) {
        return status == ProjectStatus.CLOSED;
    }
}
