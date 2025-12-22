package edu.raijin.finance.employee.infrastructure.adapter.in.messaging.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.infrastructure.adapter.messaging.event.identity.UserEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.employee.domain.model.Employee;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEventMapper {

    Employee toDomain(UserEvent event);
}
