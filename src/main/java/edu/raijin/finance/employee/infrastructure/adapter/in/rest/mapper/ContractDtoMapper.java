package edu.raijin.finance.employee.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.employee.domain.model.Contract;
import edu.raijin.finance.employee.infrastructure.adapter.in.rest.dto.contract.AddContractDto;
import edu.raijin.finance.employee.infrastructure.adapter.in.rest.dto.contract.ContractDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContractDtoMapper {

    Contract toDomain(AddContractDto dto);

    ContractDto toDto(Contract domain);
}
