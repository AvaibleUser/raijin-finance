package edu.raijin.finance.salary.infrastructure.adapter.out.messaging.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.SuspensionEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.salary.domain.model.Suspension;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SuspensionEventMapper {

    Suspension toDomain(SuspensionEvent event);

    @Mapping(target = "transactionDate", source = "createdAt")
    SuspensionEvent toEvent(Suspension domain);

    default LocalDate map(Instant instant) {
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
