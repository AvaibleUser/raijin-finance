package edu.raijin.finance.salary.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.salary.domain.model.Discount;
import edu.raijin.finance.salary.infrastructure.adapter.in.rest.dto.discount.AddDiscountDto;
import edu.raijin.finance.salary.infrastructure.adapter.in.rest.dto.discount.DiscountDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiscountDtoMapper {

    Discount toDomain(AddDiscountDto dto);

    DiscountDto toDto(Discount domain);
}
