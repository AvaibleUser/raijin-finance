package edu.raijin.finance.salary.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.salary.domain.model.Discount;
import edu.raijin.finance.salary.infrastructure.adapter.out.persistence.entity.DiscountsEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiscountEntityMapper {

    @Mapping(target = "employeeId", source = "employee.id")
    Discount toDomain(DiscountsEntity entity);

    @Mapping(target = "employee.id", source = "employeeId")
    DiscountsEntity toEntity(Discount domain);
}
