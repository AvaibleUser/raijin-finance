package edu.raijin.finance.salary.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.salary.domain.model.Suspension;
import edu.raijin.finance.salary.infrastructure.adapter.in.rest.dto.suspension.AddSuspensionDto;
import edu.raijin.finance.salary.infrastructure.adapter.in.rest.dto.suspension.SuspensionDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SuspensionDtoMapper {

    Suspension toDomain(AddSuspensionDto dto);

    SuspensionDto toDto(Suspension domain);
}
