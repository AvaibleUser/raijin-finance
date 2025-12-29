package edu.raijin.finance.finance.infrastructure.adapter.out.messaging.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.PayrollEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.finance.domain.model.Payroll;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PayrollEventMapper {

    Payroll toDomain(PayrollEvent event);

    PayrollEvent toEvent(Payroll domain);
}
