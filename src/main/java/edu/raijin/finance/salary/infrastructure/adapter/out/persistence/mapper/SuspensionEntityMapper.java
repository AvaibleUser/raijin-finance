package edu.raijin.finance.salary.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.salary.domain.model.Suspension;
import edu.raijin.finance.salary.infrastructure.adapter.out.persistence.entity.SuspensionsEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SuspensionEntityMapper {

    @Mapping(target = "employeeId", source = "employee.id")
    Suspension toDomain(SuspensionsEntity entity);

    @Mapping(target = "employee.id", source = "employeeId")
    SuspensionsEntity toEntity(Suspension domain);
}
