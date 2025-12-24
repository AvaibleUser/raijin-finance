package edu.raijin.finance.finance.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.finance.domain.model.Payroll;
import edu.raijin.finance.finance.infrastructure.adapter.out.persistence.entity.PayrollEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PayrollEntityMapper {

    @Mapping(target = "employeeId", source = "employee.id")
    Payroll toDomain(PayrollEntity entity);

    @Mapping(target = "employee.id", source = "employeeId")
    PayrollEntity toEntity(Payroll domain);
}
