package edu.raijin.finance.salary.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.salary.domain.model.Bonus;
import edu.raijin.finance.salary.infrastructure.adapter.out.persistence.entity.BonusesEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BonusEntityMapper {

    @Mapping(target = "employeeId", source = "employee.id")
    Bonus toDomain(BonusesEntity entity);

    @Mapping(target = "employee.id", source = "employeeId")
    BonusesEntity toEntity(Bonus domain);
}
