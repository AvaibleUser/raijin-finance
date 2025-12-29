package edu.raijin.finance.finance.infrastructure.adapter.out.messaging.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.IncomeEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.finance.domain.model.Income;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IncomeEventMapper {

    Income toDomain(IncomeEvent event);

    IncomeEvent toEvent(Income domain);
}
