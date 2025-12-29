package edu.raijin.finance.salary.infrastructure.adapter.out.messaging.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.DiscountEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.salary.domain.model.Discount;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiscountEventMapper {

    Discount toDomain(DiscountEvent event);

    @Mapping(target = "transactionDate", source = "createdAt")
    DiscountEvent toEvent(Discount domain);

    default LocalDate map(Instant instant) {
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
