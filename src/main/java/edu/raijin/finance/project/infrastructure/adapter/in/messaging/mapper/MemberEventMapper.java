package edu.raijin.finance.project.infrastructure.adapter.in.messaging.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.MemberEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.project.domain.model.Member;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberEventMapper {

    @Mapping(target = "employeeId", source = "userId")
    Member toDomain(MemberEvent event);
}
