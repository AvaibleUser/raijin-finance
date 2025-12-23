package edu.raijin.finance.finance.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.finance.domain.model.Income;
import edu.raijin.finance.finance.infrastructure.adapter.out.persistence.entity.IncomesEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IncomeEntityMapper {

    @Mapping(target = "projectId", source = "project.id")
    Income toDomain(IncomesEntity entity);

    @Mapping(target = "project.id", source = "projectId")
    IncomesEntity toEntity(Income domain);
}
