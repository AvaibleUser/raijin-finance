package edu.raijin.finance.finance.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.finance.domain.model.Income;
import edu.raijin.finance.finance.infrastructure.adapter.in.rest.dto.income.AddIncomeDto;
import edu.raijin.finance.finance.infrastructure.adapter.in.rest.dto.income.IncomeDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IncomeDtoMapper {

    Income toDomain(AddIncomeDto dto);

    IncomeDto toDto(Income domain);
}
