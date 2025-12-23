package edu.raijin.finance.salary.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.salary.domain.model.Bonus;
import edu.raijin.finance.salary.infrastructure.adapter.in.rest.dto.bonus.AddBonusDto;
import edu.raijin.finance.salary.infrastructure.adapter.in.rest.dto.bonus.BonusDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BonusDtoMapper {

    Bonus toDomain(AddBonusDto dto);

    BonusDto toDto(Bonus domain);
}
