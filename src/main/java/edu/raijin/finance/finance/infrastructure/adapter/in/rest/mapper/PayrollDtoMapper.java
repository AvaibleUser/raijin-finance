package edu.raijin.finance.finance.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.finance.domain.model.Payroll;
import edu.raijin.finance.finance.infrastructure.adapter.in.rest.dto.payroll.AddPayrollDto;
import edu.raijin.finance.finance.infrastructure.adapter.in.rest.dto.payroll.PayrollDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PayrollDtoMapper {

    Payroll toDomain(AddPayrollDto dto);

    PayrollDto toDto(Payroll domain);
}
